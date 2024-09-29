package math.client.connection;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author dctho
 */
public class ConnectionUtils {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    // Khởi tạo kết nối đến server
    public ConnectionUtils() throws IOException {
        socket = new Socket(ConnectionConstants.SERVER_HOST, ConnectionConstants.SERVER_PORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendData(Object data) throws IOException {
        outputStream.writeObject(data);
        outputStream.flush();
    }

    public Object receiveData() throws IOException, ClassNotFoundException {
        return inputStream.readObject();
    }

    public void closeConnection() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
