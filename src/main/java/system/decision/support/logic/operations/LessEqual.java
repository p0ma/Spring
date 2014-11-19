package system.decision.support.logic.operations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("less_equal")
public class LessEqual extends LogicalOperation{

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
