package entities.drilling.parameters;

import entities.drilling.well.MyValidationException;
import localization.LocalizationUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Function extends Parameter implements IParameterListener {

    private boolean finalResult = false;
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
        Double value = function();
        if (value.isInfinite() || value.isNaN()) {
            value = 0d;
        }
        setValue(
                value);
        calculating = false;
        finalResult = true;
    }

    protected final void registerDependentParameter(Class<?> parameter) {
        try {
            parameters.add(parameter);
            parametersModel.provideListenerToParameter(this, parameter);
        } catch (NullPointerException e) {
            System.out.println("Parameter: " + parameter.getSimpleName());
            System.out.println("This Parameter: " + this.getParameterName());
            System.out.println("Model: " + this.getParametersModel());
        }
    }

    protected Double getParameterValue(Class<?> parameter) {
        if (parameters.contains(parameter)) return parametersModel.getParameterValue(parameter);
        else {
            registerDependentParameter(parameter);
            return parametersModel.getParameterValue(parameter);
        }
    }

    @Override
    public final Double getValue() throws CrossComputingException {
        if (calculating) {
            calculating = false;
            throw new CrossComputingException("123");
        }
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
    public void setParameterValue(Double value) throws MyValidationException {
        throw new MyValidationException(
                LocalizationUtils.getMessage("function.cantBeSet"), this);
    }

    @Override
    public final void parameterChange(ParameterChangeEvent parameterChangeEvent) {
        finalResult = false;
    }

    public void setFinalResult(boolean finalResult) {
        this.finalResult = finalResult;
    }
}
