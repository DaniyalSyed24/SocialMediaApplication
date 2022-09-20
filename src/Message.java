import org.json.simple.JSONObject;

public class Message {
    // class name to be used as tag in JSON representation
    private static final String _class = Message.class.getSimpleName();

    // {"_class":"Message", "from":"Bob", "when":47, "body":"Hello again!"}

    private final String from; // from
    private final long when; // timestamp
    private final String body; // message


    // Constructor; throws NullPointerException if arguments are null
    public Message(String author, String body, long timestamp) {
        if (body == null || author == null)
            throw new NullPointerException();
        this.from = author;
        this.body = body;
        this.when = timestamp;
    }


    public String getFrom() {
        return from;
    }

    public String getBody() {
        return body;
    }

    public long getWhen() {
        return when;
    }

    @Override
    public String toString() {
        return "Message{" +
                "author='" + from + '\'' +
                ", timestamp=" + when +
                ", body='" + body + '\'' +
                '}';
    }

    // ***** JSON ***** //

    // Serializes this object into a JSONObject
    @SuppressWarnings("unchecked")
    public Object toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("_class", _class);
        obj.put("author", from);
        obj.put("timestamp", when);
        obj.put("body", body);
        return obj;
    }

    // Deserialize a Message instance from a JSONObject.

    public static Message fromJSON(Object val) {
        try {
            JSONObject obj = (JSONObject) val;
            // check for matching class name
            if (!_class.equals(obj.get("_class")))
                return null;

            String author = (String) obj.get("author");
            String body = (String) obj.get("body");
            long timestamp = (long) obj.get("timestamp");

            return new Message(body, author, timestamp);
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }

}
