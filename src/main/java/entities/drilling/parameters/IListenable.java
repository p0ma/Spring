package entities.drilling.parameters;

public interface IListenable {

    public void addListener(IParameterListener parameterListener);

    public void detachListener(IParameterListener parameterListener);
}
