package system.decision.support.logic;

public class Explanation {
    private boolean predicateResult;
    private Predicate from;
    private InferenceResult to;
    private String why;

    public Explanation() {

    }

    public Explanation(boolean predicateResult, Predicate from, InferenceResult to, String why) {
        this.predicateResult = predicateResult;
        this.from = from;
        this.to = to;
        this.why = why;
    }

    public String getExplanation() {
        StringBuilder stringBuilder = new StringBuilder(500);
        stringBuilder.append("predicate ");
        stringBuilder.append(from.getName());
        stringBuilder.append(" leads to");
        if(to instanceof Conclusion) {
            stringBuilder.append(" conclusion that ");
            stringBuilder.append(((Conclusion)to).getMessage());

        } else if(to instanceof Predicate) {
            stringBuilder.append(" predicate ");
            stringBuilder.append(((Predicate)to).getName());
        }

        stringBuilder.append(" because ");
        stringBuilder.append(from.getLogicalOperation().getLogicalOperation());
        stringBuilder.append(" is ");
        stringBuilder.append(predicateResult);
        return stringBuilder.toString();
    }

    public boolean isPredicateResult() {
        return predicateResult;
    }

    public void setPredicateResult(boolean predicateResult) {
        this.predicateResult = predicateResult;
    }

    public Predicate getFrom() {
        return from;
    }

    public void setFrom(Predicate from) {
        this.from = from;
    }

    public InferenceResult getTo() {
        return to;
    }

    public void setTo(InferenceResult to) {
        this.to = to;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
}
