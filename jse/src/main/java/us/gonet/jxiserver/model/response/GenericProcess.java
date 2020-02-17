package us.gonet.jxiserver.model.response;

public class GenericProcess {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GenericProcess{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
