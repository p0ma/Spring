package entities.drilling.model;

import entities.auth.User;
import entities.drilling.model.well.Well;

import javax.persistence.*;

@Entity
public class WorkingDataSet {

    public static WorkingDataSet build() {
        WorkingDataSet workingDataSet = new WorkingDataSet();
        workingDataSet.setParametersModel(ParametersModel.build());
        workingDataSet.setWell(Well.build());
        return workingDataSet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ParametersModel parametersModel;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Well well;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "workingDataSet")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkingDataSet() {
    }

    public WorkingDataSet(ParametersModel parametersModel, Well well, Long id) {
        this.id = id;
        this.parametersModel = parametersModel;
        this.well = well;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParametersModel getParametersModel() {
        return parametersModel;
    }

    public void setParametersModel(ParametersModel parametersModel) {
        this.parametersModel = parametersModel;
    }

    public Well getWell() {
        return well;
    }

    public void setWell(Well well) {
        this.well = well;
    }
}
