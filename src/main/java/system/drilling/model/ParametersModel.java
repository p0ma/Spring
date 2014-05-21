package system.drilling.model;

import com.springapp.mvc.context.provider.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;
import system.drilling.model.parameters.*;
import system.drilling.model.parameters.Parameter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ParametersModel implements IParametersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = Parameter.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Map<String, IParameter> parameterMap = new HashMap<String, IParameter>();

    @Transient
    private boolean changed;

    @Transient
    private ApplicationContext modelContext;

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

    public Map<String, IParameter> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, IParameter> parameterMap) {
        this.parameterMap = parameterMap;
        setChanged(true);
    }

    public ApplicationContext getModelContext() {
        return modelContext;
    }

    public void setModelContext(ApplicationContext modelContext) {
        this.modelContext = modelContext;
    }

    public ParametersModel() {
        this.changed = false;
        this.modelContext = ApplicationContextProvider.getApplicationContext();
    }

    public ParametersModel(ApplicationContext modelContext) {
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

    public IParameter getParameter(Class<?> key) {
        try {
            return parameterMap.get(key.getSimpleName());
        } catch (NullPointerException e) {
            return addParameter(key).getParameter(key);
        }
    }

    public Double getParameterValue(Class<?> key) {
        try {
            return getParameterValue(key.getSimpleName());
        } catch (NullPointerException e) {
            return addParameter(key).getParameterValue(key.getSimpleName());
        }
    }

    private ParametersModel addParameter(String key, IParameter parameter) {
        parameterMap.put(key, parameter);
        parameter.setModel(this);
        setChanged(true);
        return this;
    }

    public ParametersModel addParameter(IParameter parameter) {
        addParameter(parameter.getClass().getSimpleName(), parameter);
        return this;
    }

    public ParametersModel setParameterValue(String key, Double value) {
        parameterMap.get(key).setValue(value);
        setChanged(true);
        return this;
    }

    public ParametersModel setParameterValue(Class<?> key, Double value) {
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

    @Override
    public Map<String, String> getAllValues() {
        Map<String, String> map = new HashMap<String, String>();
        try{
        int size = parameterMap.size();
        for(IParameter parameter : parameterMap.values())
        {
            map.put(parameter.getClass().getSimpleName(), ((Double)(parameter.getValue())).toString());
            if(parameterMap.values().size() > size) return getAllValues();
        }
        }catch (CrossComputingException e) {
            e.printStackTrace();
        }
        return map;
    }

    private ParametersModel addParameter(Class<?> key) {
        addParameter((IParameter) modelContext.getBean(key));
        return this;
    }

    private void renewResults() {
        for (IParameter parameter : parameterMap.values()) {
            if (parameter instanceof Function) {
                ((Function) parameter).setFinalResult(false);
            }
        }
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
