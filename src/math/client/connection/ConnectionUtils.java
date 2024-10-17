package math.client.connection;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author dctho
 */
public class ConnectionUtils {
    private final Socket socket;
    private PrintWriter outputStream;
    private BufferedReader inputStream;

    // Khởi tạo kết nối đến server
    public ConnectionUtils() throws IOException {
        socket = new Socket(ConnectionConstants.SERVER_HOST, ConnectionConstants.SERVER_PORT);
    }

    public void sendData(String data) throws IOException {
        outputStream = new PrintWriter(socket.getOutputStream(), true);
        outputStream.println(data);
        outputStream.flush();
    }

    public String receiveData() throws IOException, ClassNotFoundException {
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return inputStream.readLine();
    }

    public void closeConnection() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
