package math.client.service.utils;

import com.google.gson.Gson;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 *
 * @author dctho
 */
public class ConnectionUtil {

    private static final Logger log = LoggerFactory.getLogger(ConnectionUtil.class);
    private Socket socket;
    private PrintWriter outputStream;
    private BufferedReader inputStream;
    private static final ConnectionUtil instance = new ConnectionUtil();

    private ConnectionUtil() {
        try {
            socket = new Socket(Constants.SERVER_HOST, Constants.SERVER_PORT);
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            log.error("Failed to create connection: ", e);
        } catch (Exception e) {
            log.error("Runtime exception when initialize connection: ", e);
        }
    }

    public static ConnectionUtil getInstance() {
        return instance;
    }

    public void sendData(BaseRequest request) {
        Gson gson = new Gson();
        outputStream.println(gson.toJson(request));
    }

    public BaseResponse<?> receiveData() {
        try {
            Gson gson = new Gson();
            String responseJson = inputStream.readLine();

            if (Objects.nonNull(responseJson))
                return gson.fromJson(responseJson, BaseResponse.class);

            return new BaseResponse<>(404, false, null, "No response from server");
        } catch (IOException e) {
            log.error("Cannot read data from input stream", e);
            return new BaseResponse<>(500, false, null, "Cannot read data from response");
        }
    }

    public void closeConnection() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            log.error("Cannot close connection", e);
        }
    }
}
