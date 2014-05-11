package system.decision.support.logic;

import system.decision.support.logic.operations.LogicalOperation;

public interface IPredicate extends InferenceResult{
    public LogicalOperation getLogicalOperation();
    public void setFiresOnTrue(InferenceResult predicate);
    public void setFiresOnFalse(InferenceResult predicate);
    public Explanation fire();
    public void setName(String name);
}
