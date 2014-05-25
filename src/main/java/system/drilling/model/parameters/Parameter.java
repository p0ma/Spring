package system.drilling.model.parameters;

import system.drilling.model.IParametersModel;
import system.drilling.model.ParametersModel;
import system.drilling.model.well.MyValidationException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "parameter_name", discriminatorType = DiscriminatorType.STRING, length = 50)
public abstract class Parameter implements IParameter {

    protected Double value;

    public Double getParameterValue() {
        return value;
    }

    @Transient
    protected IParametersModel model;

    @Transient
    private String groupName;

    @Transient
    private String parameterName;

    @Override
    public String getGroupName() {
        return groupName;
    }

    protected void setupGroupName() {
        setGroupName("Parameters");
    }

    protected void setupParameterName() {
        setParameterName("Parameter");
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String getParameterName() {
        return parameterName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private final void init() {
        setupGroupName();
        setupParameterName();
    }

    public Parameter() {
        init();
        value = new Double(0);
    }

    public Parameter(Double value) {
        init();
        this.value = value;
    }

    public void setParameterValue(Double value) throws MyValidationException {
         setValue(value);
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
    public final void setModel(ParametersModel parametersModel) {
        this.model = parametersModel;
    }

    public final IParametersModel getModel() {
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
}
