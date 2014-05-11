package system.drilling.model.dataset;

import system.drilling.model.Situation;
import org.springframework.stereotype.Component;

@Component
public class DataSet {
    private float fluidIntensity; //litre per minute
    private Situation situation;

    public void setFluidIntensity(float fluidIntensity) {
        this.fluidIntensity = fluidIntensity;
    }

    public float getFluidIntensity() {
        return fluidIntensity;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
}
