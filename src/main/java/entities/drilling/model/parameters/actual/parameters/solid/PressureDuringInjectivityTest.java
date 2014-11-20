package entities.drilling.model.parameters.actual.parameters.solid;

import entities.drilling.model.parameters.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("pressure_during_injectivity_test")
public class PressureDuringInjectivityTest extends Parameter {

    @Override
    public void setupRound() {
        round = 3;
    }

    @Override
    public void setupUnit() {
        this.unit = "bar";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Pressure during injectivity test");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Solid");
    }
}
