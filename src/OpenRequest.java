import org.json.simple.JSONObject;

public class OpenRequest extends Request {

    private static final String _class = OpenRequest.class.getSimpleName();

    // establishes the client's identity
    // creates a channel by that name if it does not already exist.
    // The request also subscribes the client to its own channel.
    // The request always succeeds.


    private final String identity;


    public OpenRequest(String identity) {
        if (identity == null)
            throw new NullPointerException();
        this.identity = identity;
    }

    String getIdentity() {
        return identity;
    }

    // Serializes this object into a JSONObject
    @SuppressWarnings("unchecked")
    public Object toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("_class", _class);
        obj.put("identity", identity);
        return obj;
    }

    // Tries to deserialize an EchoRequest instance from a JSONObject.
    // Returns null if deserialization was not successful (e.g. because a
    // different object was serialized).
    public static OpenRequest fromJSON(Object val) {
        try {
            JSONObject obj = (JSONObject) val;
            if (!_class.equals(obj.get("_class"))) //check if it's the right class to deserialize.
                return null;
            String identity = (String) obj.get("identity");

            return new OpenRequest(identity);

        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }
}
