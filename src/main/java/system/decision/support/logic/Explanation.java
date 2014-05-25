package system.decision.support.logic;

public class Explanation {
    private boolean predicateResult;
    private Predicate from;
    private Predicate to;
    private String why;

    public Explanation() {

    }

    public Explanation(boolean predicateResult, Predicate from, Predicate to, String why) {
        this.predicateResult = predicateResult;
        this.from = from;
        this.to = to;
        this.why = why;
    }

    public String getExplanation() {
        StringBuilder stringBuilder = new StringBuilder(500);
        if(from != null) {
            if(from.isAQuestion()) {
                stringBuilder.append("Question '");
                stringBuilder.append(from.getConclusion().getMessage());
                stringBuilder.append("'");
            } else {
                stringBuilder.append("Predicate '");
                stringBuilder.append(from.getName());
                stringBuilder.append("'");
            }
            stringBuilder.append(" leads to ");
        }
        if(to != null) {
            if(to.isAConclusion()) {
                stringBuilder.append("conclusion '");
                stringBuilder.append(to.getConclusion().getMessage());
                stringBuilder.append("'");
            } else if(to.isAQuestion()) {
                stringBuilder.append("question '");
                stringBuilder.append(to.getConclusion().getMessage());
                stringBuilder.append("'");
            } else if(to instanceof Predicate) {
                stringBuilder.append("predicate '");
                stringBuilder.append(to.getName());
                stringBuilder.append("'");
            }
        } else {
            stringBuilder.append(" nothing");
        }

        stringBuilder.append(" because ");
        if(from.isAQuestion()) {
            stringBuilder.append(" answer was ");
            if(predicateResult == true) {
                stringBuilder.append(" 'YES' ");
            } else {
                stringBuilder.append(" 'NO' ");
            }
        } else {
            stringBuilder.append("'" + from.getLogicalOperation().getLogicalOperationName() + "'");
            stringBuilder.append(" is ");
            stringBuilder.append(predicateResult);
        }
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

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public Predicate getTo() {
        return to;
    }

    public void setTo(Predicate to) {
        this.to = to;
    }
}
