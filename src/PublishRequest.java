import org.json.simple.JSONObject;

public class PublishRequest extends Request {

    private static final String _class = PublishRequest.class.getSimpleName();

    // publishes a message on the client's channel
    // The timestamp of the message is ignored
    // the server will issue its own timestamp for the message when storing it.
    // The request fails if the channel does not exist or if the message is too big.

    //{"_class":"PublishRequest", "identity":"Alice", "message":{"_class":"Message", "from":"Bob", "when":0, "body":"Hello again!"}}


    private final String message;


    public PublishRequest(String message) {
        // check for null
        if (message == null)
            throw new NullPointerException();
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


    @SuppressWarnings("unchecked")
    public Object toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("_class", _class);
        obj.put("message", message);
        return obj;
    }

    public static PublishRequest fromJSON(Object val) {
        try {
            JSONObject obj = (JSONObject) val;
            if (!_class.equals(obj.get("_class")))
                return null;
            String message = (String) obj.get("message");
            return new PublishRequest(message);
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }
}
