package system.decision.support.logic.operations;

import system.drilling.model.parameters.IComparable;
import system.drilling.model.parameters.IParameter;

public abstract class LogicalOperation {

    protected IComparable operand1;
    protected IComparable operand2;

    public LogicalOperation(IComparable operand1, IComparable operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public abstract boolean getResult();

    public String getLogicalOperation() {
        return operand1.getName() + " " + getLogicalOperationSignature() + " " + operand2.getName();
    }

    public abstract String getLogicalOperationSignature();
}
