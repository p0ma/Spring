package system.drilling.model.parameters.actual.parameters.mud;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;
import system.drilling.model.parameters.actual.parameters.shoe.ShoeVerticalDepth;
import system.drilling.model.parameters.actual.parameters.solid.MudDensityDuringSolidTest;
import system.drilling.model.parameters.actual.parameters.solid.PressureDuringInjectivityTest;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("maximum_allowable_density")
public class MaximumAllowableDensity extends Function {

    @Override
    public void setupUnit() {
        this.unit = "kg/m<sup>3</sup>";
    }

    @Override
    protected Double function() throws CrossComputingException {
        Double mudDensityDuringSolidTest = getParameterValue(MudDensityDuringSolidTest.class);
        Double pressureDuringInjectivityTest = getParameterValue(PressureDuringInjectivityTest.class);
        Double shoeVerticalDepth = getParameterValue(ShoeVerticalDepth.class);
        Double result = mudDensityDuringSolidTest +
                pressureDuringInjectivityTest * Math.pow(10, 6) /
                        (shoeVerticalDepth * 9.81);
        return result;
    }

    @Override
    public String getFormula() {
        return "mudDensityDuringSolidTest + pressureDuringInjectivityTest * Math.pow(10, 6) / (shoeVerticalDepth * 9.81)";
    }

    private Double function(Double mudDensityDuringSolidTest, Double pressureDuringInjectivityTest,
                            Double shoeVerticalDepth) {
        Double result = mudDensityDuringSolidTest +
                pressureDuringInjectivityTest * Math.pow(10, 6) /
                        (shoeVerticalDepth * 9.81);
        return result;
    }

    @Override
    public void setupParameterName() {
        setParameterName("Maximum allowable density");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Mud");
    }
}
