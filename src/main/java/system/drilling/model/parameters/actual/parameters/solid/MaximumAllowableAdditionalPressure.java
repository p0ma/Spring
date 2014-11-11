package system.drilling.model.parameters.actual.parameters.solid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;
import system.drilling.model.parameters.actual.parameters.mud.MaximumAllowableDensity;
import system.drilling.model.parameters.actual.parameters.mud.MudDensity;
import system.drilling.model.parameters.actual.parameters.well.WellVerticalDepth;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("maximum_allowable_additional_pressure")
public class MaximumAllowableAdditionalPressure extends Function {
    @Override
    public void setupUnit() {
        this.unit = "bar";
    }

    @Override
    protected Double function() throws CrossComputingException {
        Double maximumAllowableDensity = getParameterValue(MaximumAllowableDensity.class);
        Double mudDensity = getParameterValue(MudDensity.class);
        Double wellVerticalDepth = getParameterValue(WellVerticalDepth.class);
        Double result = (maximumAllowableDensity -
                mudDensity) * wellVerticalDepth * 9.81 / Math.pow(10, 6);
        return result;
    }

    @Override
    public String getFormula() {
        return "(maximumAllowableDensity - mudDensity) * wellVerticalDepth * 9.81 / Math.pow(10, 6)";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Maximum allowable additional pressure");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Solid");
    }
}
