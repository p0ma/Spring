package entities.drilling.model.parameters.actual.parameters.mud;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import entities.drilling.model.parameters.actual.parameters.solid.MudDensityDuringSolidTest;
import entities.drilling.model.parameters.actual.parameters.solid.PressureDuringInjectivityTest;
import entities.drilling.model.parameters.actual.parameters.well.ActualWellDepth;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("MaximumAllowableDensity")
public class MaximumAllowableDensity extends Function {

    @Override
    protected Double function() throws CrossComputingException {
        Double mudDensityDuringSolidTest = getParameterValue(MudDensityDuringSolidTest.class);
        Double pressureDuringInjectivityTest = getParameterValue(PressureDuringInjectivityTest.class);
        Double actualWellDepth = getParameterValue(ActualWellDepth.class);
        Double result = mudDensityDuringSolidTest +
                pressureDuringInjectivityTest * Math.pow(10, 6) /
                        (actualWellDepth * 9.81);
        return result;
    }

    private Double function(Double mudDensityDuringSolidTest, Double pressureDuringInjectivityTest,
                            Double shoeVerticalDepth) {
        Double result = mudDensityDuringSolidTest +
                pressureDuringInjectivityTest * Math.pow(10, 6) /
                        (shoeVerticalDepth * 9.81);
        return result;
    }
}
