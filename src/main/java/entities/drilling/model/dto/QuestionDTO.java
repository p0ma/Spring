package entities.drilling.model.dto;

public class QuestionDTO {

    private String name;
    private String message;

    private Long firesOnTrue;
    private Long firesOnFalse;

    public Long getFiresOnTrue() {
        return firesOnTrue;
    }

    public void setFiresOnTrue(Long firesOnTrue) {
        this.firesOnTrue = firesOnTrue;
    }

    public Long getFiresOnFalse() {
        return firesOnFalse;
    }

    public void setFiresOnFalse(Long firesOnFalse) {
        this.firesOnFalse = firesOnFalse;
    }

    public QuestionDTO() {}

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
