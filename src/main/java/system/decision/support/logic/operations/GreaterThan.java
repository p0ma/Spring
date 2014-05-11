package system.decision.support.logic.operations;

import system.drilling.model.parameters.IComparable;

public class GreaterThan extends LogicalOperation{

    public GreaterThan(IComparable operand1, IComparable operand2) {
        super(operand1, operand2);
    }

    @Override
    public boolean getResult() {
        if(operand1.compareTo(operand2) == 1) return true;
        else return false;
    }

    @Override
    public String getLogicalOperationSignature() {
        return ">";
    }
}
