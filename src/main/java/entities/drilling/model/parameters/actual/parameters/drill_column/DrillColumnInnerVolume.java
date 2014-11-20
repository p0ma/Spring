package entities.drilling.model.parameters.actual.parameters.drill_column;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("drill_column_inner_volume")
public class DrillColumnInnerVolume extends Function {

    public DrillColumnInnerVolume() {
    }

    @Override
    public void setupUnit() {
        this.unit = "m<sup>3</sup>";
    }

    @Override
    protected Double function() throws CrossComputingException {
        try {
            return parametersModel.getWorkingDataSet().getWell().getInnerVolume();
        } catch (NullPointerException e) {
            return 0d;
        }
    }

    @Override
    public String getFormula() {
        return "Inner volume";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Drill column inner volume");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Drill column");
    }
}
