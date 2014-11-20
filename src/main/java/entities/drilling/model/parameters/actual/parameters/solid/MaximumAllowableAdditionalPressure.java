package entities.drilling.model.parameters.actual.parameters.solid;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import entities.drilling.model.parameters.actual.parameters.mud.MaximumAllowableDensity;
import entities.drilling.model.parameters.actual.parameters.mud.MudDensity;
import entities.drilling.model.parameters.actual.parameters.well.WellVerticalDepth;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("maximum_allowable_additional_pressure")
public class MaximumAllowableAdditionalPressure extends Function {

    @Override
    protected Double function() throws CrossComputingException {
        Double maximumAllowableDensity = getParameterValue(MaximumAllowableDensity.class);
        Double mudDensity = getParameterValue(MudDensity.class);
        Double wellVerticalDepth = getParameterValue(WellVerticalDepth.class);
        Double result = (maximumAllowableDensity -
                mudDensity) * wellVerticalDepth * 9.81 / Math.pow(10, 6);
        return result;
    }
}
