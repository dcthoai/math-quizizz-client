package math.client.service.utils;

import com.google.gson.Gson;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.BaseResponse;
import math.client.router.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * A class to manager connection to socket server and pre handle response
 * @author dctho
 */
public class ConnectionUtil implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ConnectionUtil.class);
    private Socket socket;
    private PrintWriter outputStream;
    private BufferedReader inputStream;
    private final Gson gson = new Gson();
    private static final Map<String, Consumer<BaseResponse>> callbacks = new HashMap<>();
    private static final ConnectionUtil instance = new ConnectionUtil();
    private static boolean isListeningResponse = false;

    private ConnectionUtil() {}

    public static ConnectionUtil getInstance() {
        return instance;
    }

    private void connect() throws Exception {
        try {
            socket = new Socket(Constants.SERVER_HOST, Constants.SERVER_PORT);
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new PrintWriter(socket.getOutputStream(), true);
            log.info("Initialize socket connection successfully");
            log.info("Connected to {}:{}", Constants.SERVER_HOST, Constants.SERVER_PORT);

            if (!isListeningResponse) {     // Create a single listen thread when connection is successful
                isListeningResponse = true;
                listeningResponseFromServer();
            }
        } catch (IOException e) {
            log.error("Failed to create connection: ", e);
            throw new IOException(e);
        } catch (Exception e) {
            log.error("Runtime exception when initialize connection: ", e);
            throw new RuntimeException(e);
        }
    }

    private void handleResponseFromServer(String responseJson) {
        BaseResponse response = gson.fromJson(responseJson, BaseResponse.class);
        String action = response.getAction();

        // Handle response with registered callback, otherwise use router to find handler function based on action
        if (callbacks.containsKey(action)) {
            Consumer<BaseResponse> callback = callbacks.get(action);
            callback.accept(response);  // Run callback to handle response
            callbacks.remove(action);
        } else {
            Router.getInstance().handleAction(response);
        }
    }

    private void listeningResponseFromServer() {
        log.info("Initialize a single response listen thread");

        while (isListeningResponse) {
            try {
                String response = inputStream.readLine();

                if (Objects.isNull(response)) {
                    throw new SocketException("Connection lost or server is closed");
                }

                handleResponseFromServer(response);
            } catch (SocketException e) {
                log.error("SocketException: Connection lost or server closed the connection. Attempting to reconnect...", e);
                tryReconnect();
                return;
            } catch (IOException e) {
                log.error("IOException while reading from input stream", e);
                tryReconnect();
                return;
            } catch (Exception e) {
                log.error("Unexpected error while listening to server response", e);
                tryReconnect();
                return;
            }
        }
    }

    private void tryReconnect() {
        isListeningResponse = false;

        try {
            closeConnection();

            for (int attempt = 1; attempt <= 120; ++attempt) { // Maximum 120 attempts
                try {
                    log.info("Attempting to reconnect... (Attempt " + attempt + ")");
                    connect();
                    return;
                } catch (Exception e) {
                    log.error("Reconnect attempt failed", e);

                    try {
                        Thread.sleep(5000); // Wait 5 seconds before reconnect if it has an error
                    } catch (InterruptedException ie) {
                        log.error("Reconnect sleep interrupted", ie);
                    }
                }
            }

            log.error("Could not reconnect to the server after 120 attempts (10 minutes).");
        } catch (IOException e) {
            log.error("Cannot close connection", e);
        }
    }

    private void closeConnection() throws IOException {
        if (Objects.nonNull(outputStream)) {
            outputStream.close();
            log.info("Close output stream successfully");
        } else {
            log.info("Cannot close socket output stream. It is null");
        }

        if (Objects.nonNull(inputStream)) {
            inputStream.close();
            log.info("Close input stream successfully");
        } else {
            log.info("Cannot close socket input stream. It is null");
        }

        if (Objects.nonNull(socket) && !socket.isClosed()) {
            socket.close();
            log.info("Close socket connection successfully");
        } else {
            log.info("Cannot close socket connection. It is null");
        }
    }

    public void sendMessageToServer(BaseRequest request) {  // Send request without processing response
        outputStream.println(gson.toJson(request));
    }

    // Register a callback function to handle the response when sending a request based on the action
    public void sendMessageToServer(BaseRequest request, Consumer<BaseResponse> callback) {
        if (Objects.nonNull(request) && Objects.nonNull(request.getAction())) {
            callbacks.put(request.getAction(), callback);
        }

        sendMessageToServer(request);
    }

    @Override
    public void run() {
        try {
            connect();
        } catch (Exception e) {
            log.error("Cannot initialize socket connection. Try reconnect to server");
            tryReconnect();
        }
    }
}
