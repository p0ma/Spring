package system.drilling.model.parameters.actual.parameters.drill_column;

import com.springapp.mvc.context.provider.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;
import system.drilling.model.well.Well;
import system.drilling.service.WellService;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("drill_column_inner_volume")
public class DrillColumnInnerVolume extends Function {

    public DrillColumnInnerVolume() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        if (applicationContext != null) {
            wellService = applicationContext.getBean(WellService.class);
        }
    }

    @Override
    public void setupUnit() {
        this.unit = "m<sup>3</sup>";
    }

    @Transient
    @Autowired
    private WellService wellService;

    @Override
    protected Double function() throws CrossComputingException {
        Well well = wellService.getWell();
        return well.getInnerVolume();
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
