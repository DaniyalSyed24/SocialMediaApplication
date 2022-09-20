import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer {

    public static void main(String[] args) {
        int serverPort = 12345;
        int clientID = 0;
        Socket s;
        ServerSocket server;

        try {
            server = new ServerSocket(serverPort);
            System.out.println("The server has started, waiting for clients...");

            while (true) {
                s = server.accept();
                clientID++;
                System.out.println("Connection accepted from client: " + clientID);
                ClientHandler ser = new ClientHandler(s);
                ser.start();
            }
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
