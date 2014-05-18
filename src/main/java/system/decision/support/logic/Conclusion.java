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
