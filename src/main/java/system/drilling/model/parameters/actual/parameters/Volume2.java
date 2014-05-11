package system.drilling.model.parameters.actual.parameters;

import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

@Component
public class Volume2 extends Parameter {

    public Volume2() {
        super();
    }

    public Volume2(Double value) {
        super(value);
    }
}
