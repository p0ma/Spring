package system.drilling.model;

import system.drilling.model.parameters.IParameter;
import system.drilling.model.parameters.IParameterListener;

import java.util.Map;

public interface IParametersModel {
    public Double getParameterValue(Class<?> key);
    public ParametersModel addParameter(IParameter parameter);
    public ParametersModel setParameterValue(Class<?> key, Double value);
    public void provideListenerToParameter(IParameterListener parameterListener, Class<?> key);
    public Map<String, String> getAllValues();
    public IParameter getParameter(Class<?> key);
}
