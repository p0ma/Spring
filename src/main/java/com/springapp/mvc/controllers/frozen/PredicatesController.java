package com.springapp.mvc.controllers.frozen;

//Frozen

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import entities.auth.User;
import system.decision.support.logic.CyclingException;
import system.decision.support.logic.InferenceModel;
import system.decision.support.logic.Predicate;
import system.decision.support.logic.operations.LogicalOperation;
import entities.drilling.dto.ConclusionDTO;
import entities.drilling.dto.PredicateDTO;
import entities.drilling.dto.QuestionDTO;
import service.frozen.InferenceModelService;
import service.ParametersModelService;
import service.frozen.PredicateService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
*/

/*@Controller
@RequestMapping("/predicates")*/
public class PredicatesController {

    /*//private static final String MODEL_CONFIG_PATH = "classpath*:system/drilling/parametersModel/config.xml";

    @Autowired
    private PredicateService predicateService;

    @Autowired
    private InferenceModelService inferenceModelService;

    @RequestMapping(method = RequestMethod.GET)
    public String predicates(@AuthenticationPrincipal User user, ModelMap model) {
        List<Predicate> predicateList = predicateService.findAll();
        List<Predicate> questions = new ArrayList<Predicate>();
        List<Predicate> conclusions = new ArrayList<Predicate>();
        for (Predicate predicate : predicateList) {
            if (predicate.isAQuestion()) {
                questions.add(predicate);
            }
            if (predicate.isAConclusion()) {
                conclusions.add(predicate);
            }
        }
        predicateList.removeAll(questions);
        predicateList.removeAll(conclusions);
        model.addAttribute("predicates", predicateList);
        model.addAttribute("questions", questions);
        model.addAttribute("conclusions", conclusions);
        return "predicates";
    }

    @RequestMapping(value = "/choose_starting_predicate", method = RequestMethod.POST)
    @ResponseBody
    public String deletePipeSection(@RequestParam Long id) throws IOException, Exception {
        String msg;
        try {
            InferenceModel inferenceModel = inferenceModelService.getInferenceModel();
            Predicate predicate = predicateService.findById(id);
            inferenceModel.setStartindPredicate1(predicate);
            inferenceModelService.update(inferenceModel);
            msg = "Predicate " + predicate.getName() + " has been chosen to be a triggering";
        } catch (Exception e) {
            throw e;
        }
        return msg;
    }

    @RequestMapping(value = "/choose_starting_predicate_form", method = RequestMethod.GET)
    public String deletePipeSectionForm(ModelMap model) {
        List<Predicate> predicateList = predicateService.findAll();
        List<Predicate> predicateList1 = new ArrayList<Predicate>();
        for (Predicate predicate : predicateList) {
            if (!predicate.isAConclusion()) {
                predicateList1.add(predicate);
            }
        }
        model.addAttribute("predicates", predicateList1);
        return "choose_starting_predicate_form";
    }

    @RequestMapping(value = "/add_predicate", method = RequestMethod.POST)
    @ResponseBody
    public String addPredicate(@AuthenticationPrincipal User user,
            @Valid PredicateDTO predicateDTO
    ) throws IOException, Exception, CyclingException {

        Predicate predicate = predicateService.createFromDto(predicateDTO,
                user.getWorkingDataSet().getParametersModel());
        InferenceModel inferenceModel = inferenceModelService.getInferenceModel();

        inferenceModel.addPredicate(predicate);
        try {
            predicateService.create(predicate);
        } catch (Exception e) {
            inferenceModel.removePredicate(predicate);
            throw e;
        }

        return "Predicate has been added";
    }

    @RequestMapping(value = "/add_conclusion", method = RequestMethod.POST)
    @ResponseBody
    public String addConclusion(
            @Valid ConclusionDTO conclusionDTO
    ) throws IOException, Exception, CyclingException {

        Predicate predicate = predicateService.createFromDto(conclusionDTO);
        InferenceModel inferenceModel = inferenceModelService.getInferenceModel();

        inferenceModel.addPredicate(predicate);
        try {
            predicateService.create(predicate);
        } catch (Exception e) {
            inferenceModel.removePredicate(predicate);
            throw e;
        }

        return "Conclusion has been added";
    }

    @RequestMapping(value = "/add_question", method = RequestMethod.POST)
    @ResponseBody
    public String addConclusion(
            @Valid QuestionDTO questionDTO
    ) throws IOException, Exception, CyclingException {

        Predicate predicate = predicateService.createFromDto(questionDTO);
        InferenceModel inferenceModel = inferenceModelService.getInferenceModel();

        inferenceModel.addPredicate(predicate);
        try {
            predicateService.create(predicate);
        } catch (Exception e) {
            inferenceModel.removePredicate(predicate);
            throw e;
        }

        return "Question has been added";
    }

    @RequestMapping(value = "/edit_predicate/{id}")
    @ResponseBody
    public String editPredicate(@AuthenticationPrincipal User user,
            @Valid PredicateDTO predicateDTO, @PathVariable Long id
    ) throws IOException, Exception, CyclingException {

        Predicate predicate = predicateService.createFromDto(predicateDTO,
                user.getWorkingDataSet().getParametersModel());
        InferenceModel inferenceModel = inferenceModelService.getInferenceModel();

        predicate.setId(id);

        inferenceModel.addPredicate(predicate);
        try {
            predicateService.update(predicate);
        } catch (Exception e) {
            inferenceModel.removePredicate(predicate);
            throw e;
        }

        return "Predicate has been updated";
    }

    @RequestMapping(value = "/edit_conclusion/{id}")
    @ResponseBody
    public String editConclusion(
            @Valid ConclusionDTO conclusionDTO, @PathVariable Long id
    ) throws IOException, Exception, CyclingException {

        Predicate predicate = predicateService.createFromDto(conclusionDTO);
        InferenceModel inferenceModel = inferenceModelService.getInferenceModel();

        predicate.setId(id);

        inferenceModel.addPredicate(predicate);
        try {
            predicateService.update(predicate);
        } catch (Exception e) {
            inferenceModel.removePredicate(predicate);
            throw e;
        }

        return "Conclusion has been updated";
    }

    @RequestMapping(value = "/edit_question/{id}")
    @ResponseBody
    public String editConclusion(
            @Valid QuestionDTO questionDTO, @PathVariable Long id
    ) throws IOException, Exception, CyclingException {

        Predicate predicate = predicateService.createFromDto(questionDTO);
        InferenceModel inferenceModel = inferenceModelService.getInferenceModel();

        predicate.setId(id);

        inferenceModel.addPredicate(predicate);
        try {
            predicateService.update(predicate);
        } catch (Exception e) {
            inferenceModel.removePredicate(predicate);
            throw e;
        }

        return "Question has been updated";
    }

    @RequestMapping(value = "/delete_conclusion/{id}")
    @ResponseBody
    public String deleteConclusion(
            @PathVariable Long id
    ) throws IOException, Exception, CyclingException {

        predicateService.delete(id);

        return "Conclusion has been deleted";
    }

    @RequestMapping(value = "/delete_question/{id}")
    @ResponseBody
    public String deleteQuestion(
            @PathVariable Long id
    ) throws IOException, Exception, CyclingException {

        predicateService.delete(id);

        return "Question has been deleted";
    }

    @RequestMapping(value = "/delete_predicate/{id}")
    @ResponseBody
    public String deletePredicate(
            @PathVariable Long id
    ) throws IOException, Exception, CyclingException {

        predicateService.delete(id);

        return "Predicate has been deleted";
    }

    @ExceptionHandler(CyclingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String cyclingExceptionHandler(CyclingException e) throws IOException {
        return e.getMessage();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String bindingExceptionHandler(BindException e) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(500);
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Iterator<FieldError> iterator = fieldErrors.iterator();
        for (; iterator.hasNext(); ) {
            FieldError fieldError = iterator.next();
            stringBuilder.append("Value '" + fieldError.getRejectedValue() + "' for field " +
                    fieldError.getField() + " is not acceptable!");
            if (iterator.hasNext()) {
                stringBuilder.append("<br>");
            }
        }
        return stringBuilder.toString();
    }

    @RequestMapping(value = "/add_predicate_form", method = RequestMethod.GET)
    public String addPredicate(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("predicates", predicateService.findAll());
        model.addAttribute("parameters", user.getWorkingDataSet().getParametersModel().getParameterMap());
        model.addAttribute("logicalOperations", LogicalOperation.getLogicalOperations());
        model.addAttribute("pretext", "Adding");
        return "add_predicate_form";
    }

    @RequestMapping(value = "/add_conclusion_form", method = RequestMethod.GET)
    public String addConclusion(ModelMap model) {
        List<Predicate> predicates = predicateService.findAll();
        List<Predicate> conclusions = new ArrayList<Predicate>();
        for (Predicate predicate : predicates) {
            if (predicate.isAConclusion()) {
                conclusions.add(predicate);
            }
        }
        model.addAttribute("conclusions", conclusions);
        model.addAttribute("pretext", "Adding");
        return "add_conclusion_form";
    }

    @RequestMapping(value = "/add_question_form", method = RequestMethod.GET)
    public String addQuestion(ModelMap model) {
        List<Predicate> predicates = predicateService.findAll();
        List<Predicate> question = new ArrayList<Predicate>();
        for (Predicate predicate : predicates) {
            if (predicate.isAQuestion()) {
                question.add(predicate);
            }
        }
        model.addAttribute("questions", question);
        model.addAttribute("pretext", "Adding");
        return "add_question_form";
    }

    @RequestMapping(value = "/edit_predicate_form/{id}", method = RequestMethod.GET)
    public String editPredicate(@AuthenticationPrincipal User user, @PathVariable Long id, ModelMap model) {
        model.addAttribute("predicate", predicateService.findById(id));
        model.addAttribute("predicates", predicateService.findAll());
        model.addAttribute("parameters", user.getWorkingDataSet().getParametersModel().getParameterMap());
        model.addAttribute("logicalOperations", LogicalOperation.getLogicalOperations());
        model.addAttribute("pretext", "Editing");
        return "add_predicate_form";
    }

    @RequestMapping(value = "/edit_conclusion_form/{id}", method = RequestMethod.GET)
    public String editConclusion(@PathVariable Long id, ModelMap model) {
        model.addAttribute("predicate", predicateService.findById(id));
        List<Predicate> predicates = predicateService.findAll();
        List<Predicate> conclusions = new ArrayList<Predicate>();
        for (Predicate predicate : predicates) {
            if (predicate.isAConclusion()) {
                conclusions.add(predicate);
            }
        }
        model.addAttribute("conclusions", conclusions);
        model.addAttribute("pretext", "Editing");
        return "add_conclusion_form";
    }

    @RequestMapping(value = "/edit_question_form/{id}", method = RequestMethod.GET)
    public String editQuestion(@PathVariable Long id, ModelMap model) {
        model.addAttribute("predicate", predicateService.findById(id));
        List<Predicate> predicates = predicateService.findAll();
        List<Predicate> questions = new ArrayList<Predicate>();
        for (Predicate predicate : predicates) {
            if (predicate.isAQuestion()) {
                questions.add(predicate);
            }
        }
        model.addAttribute("predicates", predicates);
        model.addAttribute("questions", questions);
        model.addAttribute("pretext", "Editing");
        return "add_question_form";
    }*/
}