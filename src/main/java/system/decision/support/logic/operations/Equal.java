package system.decision.support.logic.operations;

import system.drilling.model.parameters.IComparable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="equal_logical_operation")
@DiscriminatorValue("equal_logical")
public class Equal extends LogicalOperation{

    public Equal(IComparable operand1, IComparable operand2) {
        super(operand1, operand2);
    }

    @Override
    public boolean getResult() {
        if(operand1.compareTo(operand2) == 0) return true;
        else return false;
    }

    @Override
    public String getLogicalOperationSignature() {
        return "=";
    }
}
