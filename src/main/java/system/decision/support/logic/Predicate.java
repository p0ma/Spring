package system.decision.support.logic;

import system.decision.support.logic.operations.LogicalOperation;

public class Predicate implements IPredicate{

    private String name;
    private LogicalOperation logicalOperation;
    private InferenceResult firesOnTrue;
    private InferenceResult firesOnFalse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Predicate(LogicalOperation logicalOperation) {
        this.logicalOperation = logicalOperation;
    }

    public LogicalOperation getLogicalOperation() {
        return logicalOperation;
    }

    public void setFiresOnTrue(InferenceResult predicate) {
        this.firesOnTrue = predicate;
    }

    public void setFiresOnFalse(InferenceResult predicate) {
        this.firesOnFalse = predicate;
    }

    public Explanation fire() {
        Explanation explanation = new Explanation();
        explanation.setFrom(this);
        explanation.setPredicateResult(logicalOperation.getResult());
        explanation.setTo(explanation.isPredicateResult() ? firesOnTrue : firesOnFalse);
        return explanation;
    }

    @Override
    public String getInferenceResult() {
        return null;
    }
}
