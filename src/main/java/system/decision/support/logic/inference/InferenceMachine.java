package system.decision.support.logic.inference;

import system.decision.support.logic.*;

public class InferenceMachine {

    private Inference inference;

    public InferenceMachine() {

    }

    public Inference getInference() {
        return inference;
    }

    public Conclusion doInference(IPredicate predicate) {
        inference = new Inference();
        Explanation explanation;
        for (; ; ) {
            if(predicate == null) return new Conclusion();
            explanation = predicate.fire();
            predicate = explanation.getTo();
            if (predicate.getConclusion() != null) {
                inference.addInferenceLink(explanation);
                return predicate.getConclusion();
            } else {
                if (predicate instanceof Predicate) {
                    predicate = (Predicate) predicate;
                    inference.addInferenceLink(explanation);
                }
            }
        }
    }
}
