package system.drilling.model.parameters;

import org.springframework.stereotype.Component;

@Component
public class Pressure extends Parameter {
    public Pressure() {
        super();
    }

    public Pressure(Double value) {
        super(value);
    }
}
