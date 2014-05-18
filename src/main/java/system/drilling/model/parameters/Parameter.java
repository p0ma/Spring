package system.drilling.model.parameters;

import system.drilling.model.IModel;
import system.drilling.model.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "parameter_name", discriminatorType = DiscriminatorType.STRING)
public abstract class Parameter implements IParameter {

    protected Double value;

    @Transient
    protected IModel model;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Parameter() {
        value = new Double(0);
    }

    public Parameter(Double value) {
        this.value = value;
    }

    public void setValue(Double value) {
        if (value != this.value) {
            try {
                if (!this.value.equals(value)) {
                    notifyListeners(this, this.value, value);
                    this.value = value;
                }
            } catch (NullPointerException e) {
                notifyListeners(this, this.value, value);
                this.value = value;
            }
        }
    }

    @Override
    public Double getValue() throws CrossComputingException {
        return value;
    }

    @Override
    public final void setModel(Model model) {
        this.model = model;
    }

    public final IModel getModel() {
        return model;
    }

    @Transient
    protected List<IParameterListener> parameterListeners = new ArrayList<IParameterListener>();

    protected void notifyListeners(Object object, Object oldValue, Object newValue) {
        for (IParameterListener parameterListener : parameterListeners) {
            parameterListener.parameterChange(new ParameterChangeEvent(object, oldValue, newValue));
        }
    }

    @Override
    public final void addListener(IParameterListener parameterListener) {
        parameterListeners.add(parameterListener);
    }

    @Override
    public final void detachListener(IParameterListener parameterListener) {
        parameterListeners.remove(parameterListener);
    }

    @Override
    public int compareTo(Object object) {
        try{
        if (this.getValue() == ((Parameter) object).getValue())
            return 0;
        else if ((this.getValue()) > ((Parameter) object).getValue())
            return 1;
        else
            return -1;
        }catch (CrossComputingException e) {
            return -2;
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
