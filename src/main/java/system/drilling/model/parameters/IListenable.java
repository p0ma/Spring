package system.drilling.model.parameters;

public interface IListenable {

    public void addListener(IParameterListener parameterListener);

    public void detachListener(IParameterListener parameterListener);
}
