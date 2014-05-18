package system.decision.support.logic.operations;

import system.drilling.model.parameters.IComparable;
import system.drilling.model.parameters.Parameter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "logical_operation_name", discriminatorType = DiscriminatorType.STRING)
public abstract class LogicalOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = Parameter.class)
    @JoinColumn(name = "operand1_id")
    protected IComparable operand1;

    @OneToOne(targetEntity = Parameter.class)
    @JoinColumn(name = "operand2_id")
    protected IComparable operand2;

    public LogicalOperation(IComparable operand1, IComparable operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public abstract boolean getResult();

    public String getLogicalOperationName() {
        return operand1.getName() + " " + getLogicalOperationSignature() + " " + operand2.getName();
    }

    public abstract String getLogicalOperationSignature();

    public IComparable getOperand1() {
        return operand1;
    }

    public void setOperand1(IComparable operand1) {
        this.operand1 = operand1;
    }

    public IComparable getOperand2() {
        return operand2;
    }

    public void setOperand2(IComparable operand2) {
        this.operand2 = operand2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
