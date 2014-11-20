package entities.drilling.model;

import entities.drilling.model.parameters.IParameter;
import entities.drilling.model.parameters.IParameterListener;
import entities.drilling.model.well.MyValidationException;

import java.util.Map;

public interface IParametersModel {
    public Double getParameterValue(Class<?> key);
    public ParametersModel addParameter(IParameter parameter);
    public ParametersModel setParameterValue(Class<?> key, Double value) throws MyValidationException;
    public void provideListenerToParameter(IParameterListener parameterListener, Class<?> key);
    public Map<String, String> getAllValues();
    public IParameter getParameter(Class<?> key);
}
