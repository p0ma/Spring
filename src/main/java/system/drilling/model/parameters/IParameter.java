package system.drilling.model.parameters;

import system.drilling.model.Model;

public interface IParameter extends IListenable, IComparable {

    public Double getValue() throws CrossComputingException;

    public void setModel(Model model);

    public void setValue(Double value);
}
