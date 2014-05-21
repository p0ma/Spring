package system.drilling.model.parameters;

import system.drilling.model.ParametersModel;

public interface IParameter extends IListenable, IComparable {

    public Double getValue() throws CrossComputingException;

    public void setModel(ParametersModel parametersModel);

    public void setValue(Double value);
}
