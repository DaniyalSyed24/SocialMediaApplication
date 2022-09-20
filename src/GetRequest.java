import org.json.simple.JSONObject;


public class GetRequest extends Request {

    private static final String _class = GetRequest.class.getSimpleName();

    public GetRequest() {}

    @SuppressWarnings("unchecked")
    public Object toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("_class", _class);
        return obj;
    }

    public static GetRequest fromJSON(Object val) {
        try {
            JSONObject obj = (JSONObject)val;
            if (!_class.equals(obj.get("_class")))
                return null;

            return new GetRequest();
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }
}
