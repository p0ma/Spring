package system.drilling.model.parameters.actual.parameters;

import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

@Component
public class Volume1 extends Parameter {

    public Volume1() {
        super();
    }

    public Volume1(Double value) {
        super(value);
    }
}
