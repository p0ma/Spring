package system.decision.support.logic;

import system.decision.support.logic.operations.LogicalOperation;

import javax.persistence.*;


@Entity
@Table(name="predicates")
public class Predicate implements IPredicate{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logical_operation_id")
    private LogicalOperation logicalOperation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fires_on_true_id")
    private Predicate firesOnTrue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fires_on_false_id")
    private Predicate firesOnFalse;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conclusion_id")
    private Conclusion conclusion;

    public Predicate() {  }

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

    public Explanation fire() {
        Explanation explanation = new Explanation();
        explanation.setFrom(this);
        explanation.setPredicateResult(logicalOperation.getResult());
        explanation.setTo(explanation.isPredicateResult() ? firesOnTrue : firesOnFalse);
        return explanation;
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
}
