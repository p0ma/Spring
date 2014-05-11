package system.drilling.model.parameters.actual.parameters;

import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;

public class Pressure extends Function {

    @Override
    protected void function() throws CrossComputingException {
        setValue(
                getParameterValue(Volume.class)
                * getParameterValue(MudVolume.class)
                        *  10
        );
    }
}
