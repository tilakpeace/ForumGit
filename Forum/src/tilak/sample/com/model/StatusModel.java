package tilak.sample.com.model;


public class StatusModel {

    private String status;
    private  String message;
    private  String mode;

    public StatusModel() {
    }

    public StatusModel(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public StatusModel(String status, String message, String mode) {
        this.status = status;
        this.message = message;
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

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
}
