/*
package system.decision.support.logic.operations;

import entities.drilling.model.parameters.IComparable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "logical_operation_name", discriminatorType = DiscriminatorType.STRING)
public abstract class LogicalOperation {

    public static List<LogicalOperation> getLogicalOperations() {
        List<LogicalOperation> list = new ArrayList<LogicalOperation>();
        list.add(new Equal());
        list.add(new GreaterEqual());
        list.add(new GreaterThan());
        list.add(new LessEqual());
        list.add(new LessThan());
        return list;
    }

    public static LogicalOperation getLogicalOperation(String signature) {
        for(LogicalOperation logicalOperation : getLogicalOperations()) {
            if(logicalOperation.getLogicalOperationSignature().equals(signature)) {
                return logicalOperation;
            }
        }
        return new Equal();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = ExpressionWrapper.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "operand1_id")
    protected IComparable operand1;

    @OneToOne(targetEntity = ExpressionWrapper.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "operand2_id")
    protected IComparable operand2;

    public LogicalOperation() {}

    public LogicalOperation(IComparable operand1, IComparable operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public abstract boolean getResult();

    public String getLogicalOperationName() {
        return ((ExpressionWrapper)operand1).getFullExpression()+ " " + getLogicalOperationSignature() + " " + ((ExpressionWrapper)operand2).getFullExpression();
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
*/
