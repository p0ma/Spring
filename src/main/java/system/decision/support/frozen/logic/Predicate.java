/*
package system.decision.support.logic;

import system.decision.support.logic.operations.LogicalOperation;

import javax.persistence.*;


@Entity
@Table(name = "predicates")
public class Predicate implements IPredicate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "logical_operation_id")
    private LogicalOperation logicalOperation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fires_on_true_id")
    private Predicate firesOnTrue;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fires_on_false_id")
    private Predicate firesOnFalse;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conclusion_id")
    private Conclusion conclusion;

    public Predicate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Predicate(LogicalOperation logicalOperation) {
        this.logicalOperation = logicalOperation;
    }

    public LogicalOperation getLogicalOperation() {
        return logicalOperation;
    }

    public void setFiresOnTrue(Predicate predicate) {
        this.firesOnTrue = predicate;
    }

    public void setFiresOnFalse(Predicate predicate) {
        this.firesOnFalse = predicate;
    }

    public Predicate fire() {
        return logicalOperation.getResult() ? firesOnTrue : firesOnFalse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogicalOperation(LogicalOperation logicalOperation) {
        this.logicalOperation = logicalOperation;
    }

    public Predicate getFiresOnTrue() {
        return firesOnTrue;
    }

    public Predicate getFiresOnFalse() {
        return firesOnFalse;
    }

    public Conclusion getConclusion() {
        return conclusion;
    }

    public void setConclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
    }

    public boolean isAQuestion() {
        if (conclusion != null) {
            return conclusion.isAQuestion();
        } else {
            return false;
        }
    }

    public boolean isAConclusion() {
        if (conclusion != null) {
            return !conclusion.isAQuestion();
        } else {
            return false;
        }
    }

    public boolean Equals(Predicate predicate) {
        if (this.getId().equals(predicate.getId())) {
            return true;
        } else {
            return false;
        }
    }
}
*/
