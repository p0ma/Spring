package com.springapp.mvc.controllers;

import com.springapp.mvc.context.provider.ModelContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import system.decision.support.logic.Conclusion;
import system.decision.support.logic.inference.Inference;
import system.decision.support.logic.inference.InferenceMachine;
import system.decision.support.logic.operations.GreaterEqual;
import system.decision.support.logic.IPredicate;
import system.decision.support.logic.Predicate;
import system.drilling.model.IModel;
import system.drilling.model.Model;
import system.drilling.model.parameters.actual.parameters.MudVolume;
import system.drilling.model.parameters.actual.parameters.Volume;
import system.drilling.model.parameters.actual.parameters.Volume1;
import system.drilling.model.parameters.actual.parameters.Volume2;
import system.drilling.model.well.Casing;
import system.drilling.model.well.Well;
import system.drilling.dto.PersonDTO;
import system.drilling.repositories.PersonNotFoundException;
import system.drilling.service.PersonService;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HelloController{

    //private static final String MODEL_CONFIG_PATH = "classpath*:system/drilling/model/config.xml";

    @Autowired
    private PersonService repositoryPersonService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        IModel mdd = getModel();
		model.addAttribute("message", mdd.getAllValues());
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("lal");
        personDTO.setLastName("lal2");
        personDTO.setId(1l);
        try {
            repositoryPersonService.update(personDTO);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        model.addAttribute("message1", repositoryPersonService.findById(1l));
		return "hello";
	}

    public IModel getModel() {

        ApplicationContext modelContext = ModelContextProvider.getApplicationContext();

        IModel model = new Model(modelContext);

        Casing casing = modelContext.getBean(Casing.class);
        casing.setHeight(2);
        casing.setWidth(3);
        Well well = modelContext.getBean(Well.class);
        System.out.println(well.getValue());
        model.setParameterValue(MudVolume.class, new Double(200))
                .setParameterValue(Volume.class, new Double(60));

        for (Map.Entry<String, String> entry : model.setParameterValue(Volume1.class, new Double(30))
                .getAllValues().entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        IPredicate predicate1 = new Predicate(
                new GreaterEqual(model.getParameter(Volume2.class), model.getParameter(MudVolume.class))
        );
        predicate1.setName("Volume2 Check");

        Predicate predicate2 = new Predicate(
                new GreaterEqual(model.getParameter(Volume1.class), model.getParameter(MudVolume.class))
        );

        predicate2.setName("Volume1 Check");

        Predicate predicate3 = new Predicate();
        predicate3.setConclusion((new Conclusion("Method1 is acceptable")));
        predicate2.setFiresOnFalse(predicate3);
        predicate1.setFiresOnFalse(predicate2);

        InferenceMachine inferenceMachine = new InferenceMachine();

        inferenceMachine.doInference(predicate1);

        Inference inference = inferenceMachine.getInference();

        System.out.println(inference.getInferenceDetails());

        return model;

    }

}