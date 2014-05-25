package system.drilling.model;

import system.drilling.model.parameters.IParameter;
import system.drilling.model.parameters.IParameterListener;
import system.drilling.model.well.MyValidationException;

import java.util.Map;

public interface IParametersModel {
    public Double getParameterValue(Class<?> key);
    public ParametersModel addParameter(IParameter parameter);
    public ParametersModel setParameterValue(Class<?> key, Double value) throws MyValidationException;
    public void provideListenerToParameter(IParameterListener parameterListener, Class<?> key);
    public Map<String, String> getAllValues();
    public IParameter getParameter(Class<?> key);
}
