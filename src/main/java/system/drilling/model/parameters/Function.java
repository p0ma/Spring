package system.drilling.model.parameters;

import java.util.ArrayList;
import java.util.List;

public abstract class Function extends Parameter implements IParameterListener {

    private boolean finalResult;
    protected boolean calculating = false;

    private List<Class<?>> parameters = new ArrayList<Class<?>>();

    public Function() {
        super();
    }

    public Function(Double value) {
        super(value);
    }

    protected abstract Double function() throws CrossComputingException;

    public final void calculate() throws CrossComputingException {
        calculating = true;
        setValue(
        function());
        calculating = false;
        finalResult = true;
    }

    protected final void registerDependentParameter(Class<?> parameter) {
        parameters.add(parameter);
        model.provideListenerToParameter(this, parameter);
    }

    protected Double getParameterValue(Class<?> parameter) {
        if (parameters.contains(parameter)) return model.getParameterValue(parameter);
        else {
            registerDependentParameter(parameter);
            return model.getParameterValue(parameter);
        }
    }

    @Override
    public final Double getValue() throws CrossComputingException {
        if (calculating) throw new CrossComputingException();
        if (!finalResult) {
            calculate();
            finalResult = true;
        }
        return value;
    }

    @Override
    public final void setValue(Double value) {
        if (value != this.value) {
            try {
                if (!this.value.equals(value)) {
                    notifyListeners(this, this.value, value);
                    this.value = value;
                    finalResult = false;
                }
            } catch (NullPointerException e) {
                notifyListeners(this, this.value, value);
                this.value = value;
                finalResult = false;
            }
        }
    }

    @Override
    public final void parameterChange(ParameterChangeEvent parameterChangeEvent) {
        finalResult = false;
    }

    public void setFinalResult(boolean finalResult) {
        this.finalResult = finalResult;
    }
}
