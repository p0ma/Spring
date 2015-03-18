package entities.drilling.parameters;

import entities.drilling.model.ParametersModel;
import entities.drilling.well.MyValidationException;
import localization.LocalizationUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "parameter_name", discriminatorType = DiscriminatorType.STRING, length = 50)
public abstract class Parameter {

    @ManyToOne(fetch = FetchType.LAZY)
    protected ParametersModel parametersModel;

    @Transient
    protected int round;

    protected Double value;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public final String getUnit() {
        return LocalizationUtils.getMessage(this.getClass().getSimpleName() + ".unit");
    }

    public Double getParameterValue() {
        return value;
    }

    public void setupRound() {
        round = 2;
    }

    public final String getGroupName() {
        return LocalizationUtils.getMessage(LocalizationUtils.packageSimpleName(this.getClass().getPackage()) +
                ".group.name");
    }

    public final String getParameterName() {
        return LocalizationUtils.getMessage(this.getClass().getSimpleName() + ".name");
    }

    private void init() {
        setupRound();
    }

    public Parameter() {
        init();
        value = 0d;
    }

    public Parameter(Double value) {
        init();
        this.value = value;
    }

    public void setParameterValue(Double value) throws MyValidationException {
        setValue(value);
    }

    public void setValue(Double value) {
        if (!value.equals(this.value)) {
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

    public Double getValue() {
        return value;
    }

    public Double getRoundedValue() throws CrossComputingException {
        return Math.round(getValue() * Math.pow(10, round)) / Math.pow(10, round);
    }

    public String getStringValue() throws CrossComputingException {
        String value = getValue().toString();
        return value;
    }

    public String getStringRoundedValue() throws CrossComputingException {
        Double value = getRoundedValue();
        String str;
        if (value == value.intValue()) {
            str = Integer.toString(value.intValue());
        } else {
            str = value.toString();
        }
        return str;
    }

    public void setParametersModel(ParametersModel parametersModel) {
        this.parametersModel = parametersModel;
    }

    public ParametersModel getParametersModel() {
        return parametersModel;
    }

    @Transient
    protected List<IParameterListener> parameterListeners = new ArrayList<IParameterListener>();

    protected void notifyListeners(Object object, Object oldValue, Object newValue) {
        for (IParameterListener parameterListener : parameterListeners) {
            parameterListener.parameterChange(new ParameterChangeEvent(object, oldValue, newValue));
        }
    }

    public final void addListener(IParameterListener parameterListener) {
        parameterListeners.add(parameterListener);
    }

    public final void detachListener(IParameterListener parameterListener) {
        parameterListeners.remove(parameterListener);
    }

    public int compareTo(Object object) {
        try {
            if (this.getValue().equals(((Parameter) object).getValue()))
                return 0;
            else if ((this.getValue()) > ((Parameter) object).getValue())
                return 1;
            else
                return -1;
        } catch (CrossComputingException e) {
            return -2;
        }
    }

    public final String getHint() {
        return LocalizationUtils.getMessage(this.getClass().getSimpleName() + ".formula");
    }
}
