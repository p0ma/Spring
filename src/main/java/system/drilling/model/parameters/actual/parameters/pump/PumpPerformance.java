package system.drilling.model.parameters.actual.parameters.pump;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("pump_performance")
public class PumpPerformance extends Parameter {

    @Override
    public void setupUnit() {
        this.unit = "l/turn";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Pump performance");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pump");
    }
}
