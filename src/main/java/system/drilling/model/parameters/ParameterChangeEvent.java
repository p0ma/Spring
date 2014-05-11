package system.drilling.model.parameters;

public class ParameterChangeEvent {

    private Object object;
    private String oldValue;
    private String newValue;

    public ParameterChangeEvent(Object object, Object oldValue, Object newValue) {

    }

    public Object getObject() {
        return object;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
