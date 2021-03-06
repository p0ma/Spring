package entities.drilling.parameters.actual.parameters.solid;

import entities.drilling.parameters.Function;
import entities.drilling.parameters.actual.parameters.mud.MaximumAllowableDensity;
import entities.drilling.parameters.actual.parameters.mud.MudDensity;
import entities.drilling.parameters.actual.parameters.well.ActualWellDepth;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("MaximumAllowableAdditionalPressure")
public class MaximumAllowableAdditionalPressure extends Function {

    @Override
    protected Double function() {
        Double maximumAllowableDensity = getParameterValue(MaximumAllowableDensity.class);
        Double mudDensity = getParameterValue(MudDensity.class);
        Double actualWellDepth = getParameterValue(ActualWellDepth.class);
        Double result = (maximumAllowableDensity -
                mudDensity) * actualWellDepth * 9.81 / Math.pow(10, 6);
        return result;
    }
}
