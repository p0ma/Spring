package entities.drilling.model;

import com.springapp.mvc.context.provider.ApplicationContextProvider;
import entities.drilling.parameters.*;
import entities.drilling.parameters.Parameter;
import entities.drilling.parameters.actual.parameters.drill_column.DrillColumnInnerVolume;
import entities.drilling.well.MyValidationException;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ParametersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private Map<String, Parameter> parameterMap = new HashMap<String, Parameter>();

    @OneToMany(targetEntity = Parameter.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "parametersModel")
    private Map<String, InputValue> inputValueMap = new HashMap<String, InputValue>();

    public Map<String, InputValue> getInputValueMap() {
        return inputValueMap;
    }

    public void setInputValueMap(Map<String, InputValue> inputValueMap) {
        this.inputValueMap = inputValueMap;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "parametersModel")
    private WorkingDataSet workingDataSet;

    public WorkingDataSet getWorkingDataSet() {
        return workingDataSet;
    }

    public void setWorkingDataSet(WorkingDataSet workingDataSet) {
        this.workingDataSet = workingDataSet;
    }

    @Transient
    private boolean changed;

    @Transient
    private ApplicationContext modelContext;

    public ParametersModel() {
        this.changed = false;
        this.modelContext = ApplicationContextProvider.getApplicationContext();
    }

    public ParametersModel initParameters() {
        for (Map.Entry<String, Function> entry : modelContext.getBeansOfType(Function.class).entrySet()) {
            if (!parameterMap.containsValue(entry.getValue())) {
                addParameter(entry.getValue().getClass());
            }
        }
        for (Map.Entry<String, InputValue> entry : modelContext.getBeansOfType(InputValue.class).entrySet()) {
            if (inputValueMap.get(entry.getValue().getClass().getSimpleName()) == null) {
                InputValue inputValue = entry.getValue();
                inputValue.setParametersModel(this);
                parameterMap.put(inputValue.getClass().getSimpleName(), inputValue);
                inputValueMap.put(inputValue.getClass().getSimpleName(), inputValue);
            } else {
                parameterMap.put(entry.getValue().getClass().getSimpleName(),
                        inputValueMap.get(entry.getValue().getClass().getSimpleName()));
            }
        }
        return this;
    }

    public Map<String, Map<String, Parameter>> getParametersByGroups() {
        Map<String, Map<String, Parameter>> map = new HashMap<String, Map<String, Parameter>>();
        Map<String, Parameter> map2 = getParameterMap();
        Map<String, Parameter> map3;
        for (Map.Entry<String, Parameter> entry : map2.entrySet()) {
            map3 = map.get(entry.getValue().getGroupName());
            if (map3 == null) {
                map3 = new HashMap<String, Parameter>();

            }

            map3.put(entry.getKey(), entry.getValue());
            map.put(entry.getValue().getGroupName(), map3);
        }
        return map;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Parameter> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Parameter> parameterMap) {
        this.parameterMap = parameterMap;
        setChanged(true);
    }

    public ApplicationContext getModelContext() {
        return modelContext;
    }

    public void setModelContext(ApplicationContext modelContext) {
        this.modelContext = modelContext;
    }

    private Double getParameterValue(String key) {
        try {
            return parameterMap.get(key).getValue();
        } catch (CrossComputingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Parameter getParameter(Class<?> key) {
        try {
            Parameter parameter = parameterMap.get(key.getSimpleName());
            if (parameter != null) {
                return parameterMap.get(key.getSimpleName());
            } else {
                return addParameter(key).getParameter(key);
            }
        } catch (NullPointerException e) {
            return addParameter(key).getParameter(key);
        }
    }

    public Parameter getParameter(String key) {
        return (Parameter) this.parameterMap.get(key);
    }

    public InputValue getInputValue(String key) {
        return this.inputValueMap.get(key);
    }

    public Double getParameterValue(Class<?> key) {
        try {
            return getParameterValue(key.getSimpleName());
        } catch (NullPointerException e) {
            return addParameter(key).getParameterValue(key.getSimpleName());
        }
    }

    private ParametersModel addParameter(String key, Parameter parameter) {
        parameterMap.put(key, parameter);
        parameter.setParametersModel(this);
        setChanged(true);
        return this;
    }

    public ParametersModel addParameter(Parameter parameter) {
        addParameter(parameter.getClass().getSimpleName(), parameter);
        return this;
    }

    public ParametersModel setParameterValue(String key, Double value) throws MyValidationException {
        parameterMap.get(key).setParameterValue(value);
        setChanged(true);
        return this;
    }

    public ParametersModel setParameterValue(Class<?> key, Double value) throws MyValidationException {
        try {
            setParameterValue(key.getSimpleName(), value);
        } catch (NullPointerException e) {
            return addParameter(key).setParameterValue(key.getSimpleName(), value);
        }
        return this;
    }

    private void provideListenerToParameter(IParameterListener parameterListener, String key) {
        parameterMap.get(key).addListener(parameterListener);
    }

    public void provideListenerToParameter(IParameterListener parameterListener, Class<?> key) {
        try {
            provideListenerToParameter(parameterListener, key.getSimpleName());
        } catch (NullPointerException e) {
            addParameter(key)
                    .provideListenerToParameter(parameterListener, key);
        }
    }

    public Map<String, String> getAllValues() {
        Map<String, String> map = new HashMap<String, String>();
        try {
            int size = parameterMap.size();
            for (Parameter parameter : parameterMap.values()) {
                map.put(parameter.getClass().getSimpleName(), ((Double) (parameter.getValue())).toString());
                if (parameterMap.values().size() > size) return getAllValues();
            }
        } catch (CrossComputingException e) {
            e.printStackTrace();
        }
        return map;
    }

    private ParametersModel addInputValue(Class<?> key) {
        if (!inputValueMap.containsKey(key.getSimpleName())) {
            addParameter((Parameter) modelContext.getBean(key));

            inputValueMap.put(key.getSimpleName(), (InputValue) modelContext.getBean(key));
        }
        return this;
    }

    private ParametersModel addParameter(Class<?> key) {
        addParameter((Parameter) modelContext.getBean(key));
        return this;
    }

    private void renewResults() {
        for (Parameter parameter : parameterMap.values()) {
            if (parameter instanceof Function) {
                ((Function) parameter).setFinalResult(false);
            }
        }
    }

    public static void main(String[] args) {
        Parameter parameter = new DrillColumnInnerVolume();

    }

    public static ParametersModel build() {
        return new ParametersModel();
    }

    public Map<String, Map<String, Parameter>> getInputParametersByGroups() {
        Map<String, Map<String, Parameter>> map = new HashMap<String, Map<String, Parameter>>();
        Map<String, InputValue> map2 = getInputValueMap();
        Map<String, Parameter> map3;
        for (Map.Entry<String, InputValue> entry : map2.entrySet()) {
            map3 = map.get(entry.getValue().getGroupName());
            if (map3 == null) {
                map3 = new HashMap<String, Parameter>();

            }

            map3.put(entry.getKey(), entry.getValue());
            map.put(entry.getValue().getGroupName(), map3);
        }
        return map;
    }

   /* public static class Builder {
        public Volume1 volume1;
        public Volume2 volume2;
        public Volume volume;
        public MudVolume innerVolume;
        {
            volume = new Volume("0");
            volume1 = new Volume1("0");
            volume2 = new Volume2("0");
            innerVolume = new MudVolume("0");
        }

        public Builder volume1(String volume1) {
            this.volume1.setValue(volume1);
            return this;
        }

        public Builder volume2(String volume2) {
            this.volume2.setValue(volume2);
            return this;
        }

        public Builder volume(String volume) {
            this.volume.setValue(volume);
            return this;
        }

        public Builder innerVolume(String innerVolume) {
            this.innerVolume.setValue(innerVolume);
            return this;
        }

        public Model build() {
            return new Model(this);
        }
    }*/
}
