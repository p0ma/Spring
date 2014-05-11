package system.decision.support.logic.inference;

import system.decision.support.logic.Explanation;
import system.decision.support.logic.InferenceResult;

import java.util.LinkedList;
import java.util.List;

public class Inference {
    private List<Explanation> inferenceChain = new LinkedList<Explanation>();

    public Inference() {}

    public void addInferenceLink(Explanation explanation) {
        inferenceChain.add(explanation);
    }

    public String getInferenceDetails() {
        StringBuilder stringBuilder = new StringBuilder(500);
        for(Explanation explanation : inferenceChain) {
            stringBuilder.append(explanation.getExplanation());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
