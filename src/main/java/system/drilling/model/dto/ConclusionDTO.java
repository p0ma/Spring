package system.drilling.model.dto;

public class ConclusionDTO {

    private String name;
    private String message;

    public ConclusionDTO () {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
