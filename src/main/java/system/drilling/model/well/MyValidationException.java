package system.drilling.model.well;

public class MyValidationException extends Exception {

    public static final String GREATER_THAN = ">";
    public static final String GREATER_EQUAL = ">=";
    public static final String LESS_EQUAL = "<=";
    public static final String LESS_THAN = "<";

    public MyValidationException(String message) {
        super(message);
    }

    public static MyValidationException messageTemplate(String template, String name, Object value) {
        String message = "";
        if(template.equals(GREATER_THAN)) message =
            "Value " + name + " must be greater than " + value.toString();
        else
        if(template.equals(GREATER_EQUAL)) message =
                "Value " + name + " must be greater equal " + value.toString();
        else
        if(template.equals(LESS_EQUAL)) message =
                "Value " + name + " must be less equal " + value.toString();
        else
        if(template.equals(LESS_THAN)) message =
                "Value " + name + " must be less than " + value.toString();
        return new MyValidationException(message);
    }
}
