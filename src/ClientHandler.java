import org.json.simple.JSONValue;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientHandler extends Thread {

    private Socket client;
    private PrintWriter toClient;
    private BufferedReader fromClient;

    // shared collections
    Map<String, Map<String, List<Message>>> channels = new HashMap<>();     // map with login as key and a new map as value;
    Map<String, List<Message>> subscribers = new HashMap<>();               // map with the subscriber as key and a list of messages as its value
    List<Message> messages = new ArrayList<>();                             // list of all the messages

    private String login;

    public String getLogin() {
        return login;
    }

    public ClientHandler(Socket socket) throws IOException {
        client = socket;
        toClient = new PrintWriter(client.getOutputStream(), true);
        fromClient = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        login = null;
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = fromClient.readLine()) != null) {
                // logging request to server console
                if (login != null)
                    System.out.println(inputLine);
                else
                    System.out.println(inputLine);

                // parse request
                Object json = JSONValue.parse(inputLine);
                Request req;

                // login request Must not be logged in already
                if (login == null && (req = OpenRequest.fromJSON(json)) != null) {
                    // set login name
                    login = ((OpenRequest) req).getIdentity();
                    // response acknowledging the login request
                    toClient.println(new SuccessResponse());

                    // create a channel for the user and subscribe him
                    synchronized (ClientHandler.class) {
                        channels.put(login, subscribers);
                        subscribers.put(login, messages);
                    }
                    continue;
                }

                if (login != null && (req = PublishRequest.fromJSON(json)) != null) {
                    String message = ((PublishRequest) req).getMessage();
                    synchronized (ClientHandler.class) {
                        messages.add(new Message(login, message, 5));
                    }
                    toClient.println(new SuccessResponse());
                }

                if (login != null && (req = SubscribeRequest.fromJSON(json)) != null) {
                    String channelName = (((SubscribeRequest) req).getChannelName());
                    ((SubscribeRequest) req).setIdentity(login);

                    synchronized (ClientHandler.class) {
                        subscribers.put(channelName, messages);
                    }
                }

                if (login != null && (req = UnsubscribeRequest.fromJSON(json)) != null) {
                    String channelName = (((UnsubscribeRequest) req).getChannelName());
                    login = ((UnsubscribeRequest) req).getIdentity();

                    synchronized (ClientHandler.class) {
                        subscribers.remove(channelName, messages);
                    }
                }


            }


        } catch (IOException e) {
            System.out.println("Exception while connected");
            System.out.println(e.getMessage());
        }
    }
}