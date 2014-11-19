package system.decision.support.logic.operations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("equal_logical")
public class Equal extends LogicalOperation{

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
