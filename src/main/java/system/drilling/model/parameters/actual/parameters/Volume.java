package system.drilling.model.parameters.actual.parameters;

import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;

@Component
public class Volume extends Function {

    public Volume() {
        super();
    }

    public Volume(Double value) {
        super(value);
    }

    @Override
    protected void function() throws CrossComputingException {
        setValue(((Double) getParameterValue(Volume1.class) +
                (Double) getParameterValue(Volume2.class) +
                (Double) getParameterValue(MudVolume.class)));
    }
}
