import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_03 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Cannot setup server on this port number. Error: " + e);
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Cannot accept client connection. Error: " + e);
        }

        try {
            in = socket.getInputStream();
        } catch (IOException e) {
            System.err.println("Cannot get socket input stream. Error: " + e);
        }

        try {
            out = new FileOutputStream("/Users/jean/Documents/GitHub/dmi_sauron/src/received/example2.json");
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Error: " + e);
        }

        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
