package system.decision.support.logic.operations;

import system.drilling.model.parameters.IComparable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="less_than_logical_operation")
@DiscriminatorValue("less_than")
public class LessThan extends LogicalOperation{

    public LessThan(IComparable operand1, IComparable operand2) {
        super(operand1, operand2);
    }

    @Override
    public boolean getResult() {
        if(operand1.compareTo(operand2) == -1) return true;
        else return false;
    }

    @Override
    public String getLogicalOperationSignature() {
        return "<";
    }

}
