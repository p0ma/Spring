package system.decision.support.logic.operations;

import system.drilling.model.parameters.IComparable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="less_equal_logical_operation")
@DiscriminatorValue("less_equal")
public class LessEqual extends LogicalOperation{

    public LessEqual(IComparable operand1, IComparable operand2) {
        super(operand1, operand2);
    }

    @Override
    public boolean getResult() {
        if(operand1.compareTo(operand2) != 1) return true;
        else return false;
    }

    @Override
    public String getLogicalOperationSignature() {
        return "<=";
    }
}
