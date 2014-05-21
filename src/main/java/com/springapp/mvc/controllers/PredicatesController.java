package com.springapp.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import system.decision.support.logic.Conclusion;
import system.decision.support.logic.Predicate;
import system.decision.support.logic.operations.GreaterEqual;
import system.decision.support.logic.operations.LessEqual;
import system.decision.support.logic.operations.LogicalOperation;
import system.drilling.model.ParametersModel;
import system.drilling.model.parameters.actual.parameters.pressure.DrillPipeOuterPressure;
import system.drilling.model.parameters.actual.parameters.pressure.MudPumpingPressure;
import system.drilling.model.parameters.actual.parameters.pressure.MudPumpingPressureLoss;
import system.drilling.repositories.exceptions.ParametersModelNotFoundException;
import system.drilling.service.ParametersModelService;
import system.drilling.service.PredicateService;

import java.util.List;

@Controller
@RequestMapping("/predicates")
public class PredicatesController {

    //private static final String MODEL_CONFIG_PATH = "classpath*:system/drilling/model/config.xml";

    @Autowired
    private PredicateService predicateService;

    @Autowired
    private ParametersModelService parametersModelService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        List<Predicate> predicateList = predicateService.findAll();
        if(predicateList.isEmpty()) {
            addSome();
            predicateList = predicateService.findAll();
        }
        model.addAttribute("predicates", predicateList);
		return "predicates";
	}

    private void addSome() {
        ParametersModel parametersModel = parametersModelService.getParametersModel();
        Predicate predicate = new Predicate();
        predicate.setName("predicate");
        Predicate predicate1 = new Predicate();
        predicate1.setName("predicate1");
        Predicate predicate2 = new Predicate();
        predicate2.setName("predicate2");
        Predicate predicate3 = new Predicate();
        predicate3.setName("predicate3");
        predicate.setFiresOnTrue(predicate1);
        predicate.setFiresOnFalse(predicate2);
        predicate1.setFiresOnTrue(predicate3);
        predicate1.setFiresOnFalse(predicate2);
        LogicalOperation logicalOperation = new GreaterEqual();
        logicalOperation.setOperand1(parametersModel.getParameter(DrillPipeOuterPressure.class));
        logicalOperation.setOperand2(parametersModel.getParameter(DrillPipeOuterPressure.class));
        predicate.setLogicalOperation(logicalOperation);
        logicalOperation = new LessEqual();
        logicalOperation.setOperand1(parametersModel.getParameter(MudPumpingPressure.class));
        logicalOperation.setOperand2(parametersModel.getParameter(MudPumpingPressureLoss.class));
        predicate2.setConclusion(new Conclusion("conclusion1:predicate2"));
        predicate3.setConclusion(new Conclusion(("conclusion2:predicate3")));

        if(parametersModel.isChanged()) {
            try {
                parametersModelService.update(parametersModel);
            } catch (ParametersModelNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }


        predicateService.create(predicate);
        predicateService.create(predicate1);
        predicateService.create(predicate2);
        predicateService.create(predicate3);
    }
}