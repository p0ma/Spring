package system.drilling.model.parameters;

import system.drilling.model.ParametersModel;
import system.drilling.model.well.MyValidationException;

public interface IParameter extends IListenable, IComparable {

    public Double getValue() throws CrossComputingException;

    public void setModel(ParametersModel parametersModel);

    public void setValue(Double value);

    void setParameterValue(Double value) throws MyValidationException;

    String getGroupName();

    String getParameterName();

}