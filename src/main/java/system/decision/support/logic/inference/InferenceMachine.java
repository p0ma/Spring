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
        InferenceResult inferenceResult = predicate;
        Explanation explanation;
        for (; ; ) {
            if(inferenceResult == null) return new Conclusion();
            explanation = predicate.fire();
            inferenceResult = explanation.getTo();
            if (inferenceResult instanceof Conclusion) {
                inference.addInferenceLink(explanation);
                return (Conclusion) inferenceResult;
            } else {
                if (inferenceResult instanceof Predicate) {
                    predicate = (Predicate) inferenceResult;
                    inference.addInferenceLink(explanation);
                }
            }
        }
    }
}
