package system.decision.support.logic;

import org.springframework.stereotype.Component;
import system.decision.support.logic.inference.Inference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "inference_model_1")
@Component
public class InferenceModel {


    @Transient
    private List<Predicate> predicateList = new ArrayList<Predicate>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private Inference inference;

    @Transient
    private Predicate lastOperated;

    @OneToOne(cascade = CascadeType.DETACH)
    private Predicate startindPredicate1;

    public Predicate getStartindPredicate1() {
        return startindPredicate1;
    }

    public void setStartindPredicate1(Predicate startindPredicate1) {
        this.startindPredicate1 = startindPredicate1;
    }

    public Predicate getLastOperated() {
        return lastOperated;
    }

    public void setLastOperated(Predicate lastOperated) {
        this.lastOperated = lastOperated;
    }

    public Inference getInference() {
        return inference;
    }

    public void setInference(Inference inference) {
        this.inference = inference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InferenceModel() {

    }

    public Inference getInference(Predicate predicate) throws AnswerIsNeededException {
        if (inference == null) {
            inference = new Inference();
        }
        for(;;) {
            Explanation explanation;
            if(predicate != null) {
                explanation = new Explanation();
                explanation.setFrom(predicate);
                if(predicate.isAQuestion()) {
                    lastOperated = predicate;
                    throw new AnswerIsNeededException(predicate.getConclusion().getMessage());
                }
                if(!predicate.isAConclusion()) {
                    Boolean result = predicate.getLogicalOperation().getResult();
                    if(result) {
                        predicate = predicate.getFiresOnTrue();
                    } else {
                        predicate = predicate.getFiresOnFalse();
                    }
                    explanation.setTo(predicate);
                    explanation.setPredicateResult(result);
                } else {
                    Inference inference1 = inference;
                    inference = null;
                    return inference1;
                }
            } else {
                return inference;
            }
            inference.addInferenceLink(explanation);
        }
    }

    public Inference continueAfterQuestion(Boolean answer) throws AnswerIsNeededException {
        Explanation explanation = new Explanation();
        explanation.setFrom(lastOperated);
        explanation.setPredicateResult(answer);
        if (answer) {
            Predicate to = lastOperated.getFiresOnTrue();
            explanation.setTo(to);
            inference.addInferenceLink(explanation);
            return getInference(to);
        } else {
            Predicate to = lastOperated.getFiresOnFalse();
            explanation.setTo(to);
            inference.addInferenceLink(explanation);
            return getInference(to);
        }
    }

    public List<Predicate> getPredicateList() {
        return predicateList;
    }

    public void setPredicateList(List<Predicate> predicateList) {
        this.predicateList = predicateList;
    }

    public void addPredicate(Predicate predicate) throws CyclingException {
        predicateList.add(predicate);
        try {
            testPredicates();
        } catch (CyclingException e) {
            predicateList.remove(predicate);
            throw e;
        }
    }

    public boolean testPredicates() throws CyclingException {
        List<Explanation> explanationList;
        List<Predicate> checkList;
        boolean result = true;
        Explanation explanation;
        for (Predicate predicate : predicateList) {
            checkList = new ArrayList<Predicate>();
            explanationList = new ArrayList<Explanation>();
            testPredicate(predicate, checkList, explanationList);
        }
        return false;
    }

    private Predicate testPredicate(Predicate predicate, List<Predicate> checkList, List<Explanation> explanationList) throws CyclingException {
        if (checkList.contains(predicate)) throw new CyclingException(cyclingPredicates(explanationList));
        if (predicate != null) {
            Explanation explanation = new Explanation();
            explanation.setFrom(predicate);
            explanation.setTo(predicate.getFiresOnFalse());
            explanationList.add(explanation);
            checkList.add(predicate);
            testPredicate(predicate.getFiresOnFalse(), checkList, explanationList);
            testPredicate(predicate.getFiresOnTrue(), checkList, explanationList);
            checkList.remove(predicate);
        } else {
            return null;
        }
        return null;
    }

    private String cyclingPredicates(List<Explanation> list) {
        StringBuilder stringBuilder = new StringBuilder(500);
        for (Explanation explanation : list) {
            stringBuilder.append("Predicate '" + explanation.getFrom().getName() +
                    "' with id '" + explanation.getFrom().getId() + "'");
            stringBuilder.append(" leads to predicate '" + explanation.getTo().getName() + "'" +
                    "' with id '" + explanation.getTo().getId() + "'\n");

        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        InferenceModel inferenceModel = new InferenceModel();
        Predicate predicate = new Predicate();
        predicate.setName("predicate0");

        Predicate predicate1 = new Predicate();
        predicate1.setName("predicate1");

        Predicate predicate2 = new Predicate();
        predicate2.setName("predicate2");

        //Predicate predicate3 = new Predicate();
        //predicate3.setName("predicate3");

        predicate.setFiresOnFalse(predicate1);
        predicate.setFiresOnTrue(predicate2);
        //predicate1.setFiresOnFalse(predicate3);
        predicate1.setFiresOnTrue(predicate2);
        //predicate2.setFiresOnFalse(predicate3);
        //predicate2.setFiresOnTrue(predicate3);
        //predicate3.setFiresOnFalse(predicate);

        try {
            inferenceModel.addPredicate(predicate);
            inferenceModel.addPredicate(predicate1);
            inferenceModel.addPredicate(predicate2);
            //inferenceModel.addPredicate(predicate3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removePredicate(Predicate predicate) {
        predicateList.remove(predicate);
    }


}
