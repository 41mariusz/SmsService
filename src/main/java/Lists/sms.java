package Lists;

public class sms {

    private String number;
    private String message;
    private long timestamp;

    public sms() {
    }

    public sms(String number, String message, long timestamp) {
        this.number = number;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

