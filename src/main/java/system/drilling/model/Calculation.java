package system.drilling.model;

import org.springframework.beans.factory.annotation.Autowired;
import system.drilling.model.dataset.DataSet;

public class Calculation {
    private Method method;

    @Autowired
    private DataSet dataSet;

    public void calculate() {
        method = chooseMethod();
        method.perform();
    }

    private Method chooseMethod() {
        Method method;

        switch (dataSet.getSituation()) {
            case Boring:
                method = new DuringBoring();
                break;
            case SPO:
                method = new DuringSPO();
                break;
            default:
                method = null;
                break;
        }
        return method;
    }
}
