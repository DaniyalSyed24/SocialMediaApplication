import org.json.simple.JSONObject;

public class SuccessResponse extends Response {

    private static final String _class = SuccessResponse.class.getSimpleName();

    public SuccessResponse() {}

    // Serializes this object into a JSONObject
    @SuppressWarnings("unchecked")
    public Object toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("_class", _class);
        return obj;
    }

    // Deserialize a SuccessResponse instance from a JSONObject.
    public static SuccessResponse fromJSON(Object val) {
        try {
            JSONObject obj = (JSONObject) val;
            // check for _class field matching class name
            if (!_class.equals(obj.get("_class")))
                return null;
            // construct the new object to return
            return new SuccessResponse();
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }
}
