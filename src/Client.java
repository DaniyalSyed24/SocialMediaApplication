import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.json.simple.*;

public class Client {
    public static void main(String[] args) throws IOException {

        int portNumber = 12345;
        String hostName = "localhost";

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                // Wrapping stream from server in Scanner rather than BufferedReader
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                //parse user and build request
                Request req;
                Response res;
                Scanner sc = new Scanner(userInput);

                try {
                    switch (sc.next()) {
                        case "o":
                            req = new OpenRequest(sc.skip(" ").nextLine());
                            new SuccessResponse();
                            break;
                        case "p":
                            req = new PublishRequest(sc.skip(" ").nextLine());
                            new SuccessResponse();
                            break;
                        case "s":
                            req = new SubscribeRequest(sc.skip(" ").nextLine());
                            new SuccessResponse();
                            break;
                        case "u":
                            req = new UnsubscribeRequest(sc.skip(" ").nextLine());
                            new SuccessResponse();
                            break;
                        case "g":
                            req = new GetRequest();
                        default:
                            res = new ErrorResponse("ILLEGAL COMMAND");
                            System.out.println(res);
                            continue;
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("ILLEGAL COMMAND");
                    continue;
                }

                // Send request to server
                out.println(req);

                // Read server response; terminate if null (i.e. server quit)
                String serverResponse;
                if ((serverResponse = in.readLine()) == null)
                    break;

                // Parse JSON response, then try to deserialize JSON
                Object json = JSONValue.parse(serverResponse);
                Response resp;

                // Try to deserialize a success response
                if (SuccessResponse.fromJSON(json) != null)
                    continue;

                // Not any known response
                System.out.println("PANIC: " + serverResponse + " parsed as " + json);
                break;
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}

