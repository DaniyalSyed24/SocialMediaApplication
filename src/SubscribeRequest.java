import org.json.simple.JSONObject;

public class SubscribeRequest extends Request{

    private static final String _class = SubscribeRequest.class.getSimpleName();

    // subscribes the client to the named channel
    // The request fails if the channel does not exist.

    private String identity;
    private final String channelName;

    public SubscribeRequest(String channelName) {
        if (channelName == null)
            throw new NullPointerException();
        this.channelName = channelName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getChannelName() {
        return channelName;
    }

    // Serialize this object in a JSONObject

    public Object toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("_class", _class);
        obj.put("identity", identity);
        obj.put("channelName", channelName);
        return obj;
    }


    public static SubscribeRequest fromJSON(Object val) {
        try {
            JSONObject obj = (JSONObject) val;
            if (!_class.equals(obj.get("_class")))
                return null;
            String channelName = (String) obj.get("channelName");
            String identity = (String) obj.get("identity");

            return new SubscribeRequest(channelName);

        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }
}


