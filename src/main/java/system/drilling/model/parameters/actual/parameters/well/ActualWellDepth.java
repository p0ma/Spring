package system.drilling.model.parameters.actual.parameters.well;

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
@DiscriminatorValue("actual_well_depth")
public class ActualWellDepth extends Function {

    public ActualWellDepth() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        if (applicationContext != null) {
            wellService = applicationContext.getBean(WellService.class);
        }
    }

    @Override
    public void setupUnit() {
        this.unit = "m";
    }

    @Transient
    @Autowired
    private WellService wellService;

    @Override
    protected Double function() throws CrossComputingException {
        Well well = wellService.getWell();
        return well.getLength();
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
