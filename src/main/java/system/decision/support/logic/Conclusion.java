package system.decision.support.logic;

import javax.persistence.*;

@Entity
@Table(name = "conclusions")
public class Conclusion{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    private Boolean isAQuestion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAQuestion() {
        return isAQuestion;
    }

    public void setIsAQuestion(Boolean isAQuestion) {
        this.isAQuestion = isAQuestion;
    }

    public Conclusion() {}

    public Conclusion(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
