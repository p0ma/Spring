package entities.drilling.model.parameters.actual.parameters.well;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("actual_well_depth")
public class ActualWellDepth extends Function {

    public ActualWellDepth() {
    }

    @Override
    public void setupUnit() {
        this.unit = "m";
    }

    @Override
    @Transactional(readOnly = true)
    protected Double function() throws CrossComputingException {
        try {
            return parametersModel.getWorkingDataSet().getWell().getLength();
        } catch (NullPointerException e) {
            return 0d;
        }
    }

    @Override
    public String getFormula() {
        return "Well depth";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Actual well depth");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Well");
    }
}
