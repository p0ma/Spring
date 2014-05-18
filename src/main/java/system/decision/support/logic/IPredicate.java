package system.decision.support.logic;

import system.decision.support.logic.operations.LogicalOperation;

public interface IPredicate {
    public LogicalOperation getLogicalOperation();
    public void setFiresOnTrue(Predicate predicate);
    public void setFiresOnFalse(Predicate predicate);
    public Conclusion getConclusion();
    public void setConclusion(Conclusion conclusion);
    public Explanation fire();
    public void setName(String name);
}
