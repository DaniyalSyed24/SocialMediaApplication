import org.json.simple.JSONObject;

public class UnsubscribeRequest extends Request {

    public static final String _class = UnsubscribeRequest.class.getSimpleName();

    private String identity;
    private final String channelName;


    public UnsubscribeRequest(String channelName) {
        if (channelName == null)
            throw new NullPointerException();
        this.channelName = channelName;
    }

    public String getIdentity() {
        return identity;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Object toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("_class", _class);
        obj.put("identity", identity);
        obj.put("channelName", channelName);
        return obj;
    }

    public static UnsubscribeRequest fromJSON(Object val) {
        try {
            JSONObject obj = (JSONObject) val;
            if (!_class.equals(obj.get("channelName")))
                return null;
            String channelName = (String) obj.get("channelName");
            String identity = (String) obj.get("identity");

            return new UnsubscribeRequest(channelName);
        }catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }
}
