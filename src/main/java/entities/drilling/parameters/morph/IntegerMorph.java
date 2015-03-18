package entities.drilling.parameters.morph;

public class IntegerMorph implements MorphValue {
    private Integer value;

    @Override
    public String representation() {
        return value.toString();
    }
}
