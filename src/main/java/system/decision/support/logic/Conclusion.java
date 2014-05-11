package system.decision.support.logic;

public class Conclusion implements InferenceResult {

    private String message;

    public Conclusion() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getInferenceResult() {
        return message;
    }
}
