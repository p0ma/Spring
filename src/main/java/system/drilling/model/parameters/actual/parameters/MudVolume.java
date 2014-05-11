package system.drilling.model.parameters.actual.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;
import system.drilling.model.well.Well;

@Component
public class MudVolume extends Function {

    @Autowired
    private Well well;

    public MudVolume() {
        super(null);
    }

    public MudVolume(Double value) {
        super(value);
    }

    @Override
    public void function() throws CrossComputingException {
        setValue(
                (Double) getParameterValue(Volume1.class)
                        *
                        (Double) well.getValue()
        );
    }
}
