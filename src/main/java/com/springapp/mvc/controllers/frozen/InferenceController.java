package com.springapp.mvc.controllers.frozen;

//Frozen

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import system.decision.support.logic.InferenceModel;
import entities.drilling.model.well.MyValidationException;
import service.frozen.InferenceModelService;
import service.ParametersModelService;
import service.WellService;

import java.io.IOException;

@Controller
@RequestMapping("/inference")*/
public class InferenceController {
/*
    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private WellService wellService;

    private InferenceModel inferenceModel;

    @Autowired
    private InferenceModelService inferenceModelService;

    *//*@RequestMapping(value = "/conclusion", method = RequestMethod.POST)
    @ResponseBody
    public String getConclusion(@Valid ConfirmationDTO confirmationDTO, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String json;
        Boolean result;
        String message;

        if(inferenceModel == null || confirmationDTO.getMessage().isEmpty()) {
            inferenceModel = inferenceModelService.getInferenceModel();
            if(inferenceModel.getStartindPredicate1() == null) {
                throw new NoStartingPredicateException();
            }
            try {
                Inference inference = inferenceModel.getInference(inferenceModel.getStartindPredicate1());
                message = inference.getInferenceDetails();
                result = true;
                inferenceModel = null;
            } catch (AnswerIsNeededException e) {
                message = e.getMessage();
                result = false;
            }
        } else {
            try {
                Inference inference = inferenceModel.continueAfterQuestion(confirmationDTO.isBoolVal());
                message = inference.getInferenceDetails();
                result = true;
                inferenceModel = null;
            } catch (AnswerIsNeededException e) {
                message = e.getMessage();
                result = false;
            }
        }

        try {

            //1. Create 'jackson' object mapper
            ObjectMapper objectMapper = new ObjectMapper();

            confirmationDTO.setBoolVal(result);
            confirmationDTO.setMessage(message);

            //2. Convert your 'bean' or 'dto' as 'json' string
            json = objectMapper.writeValueAsString(confirmationDTO);

        } catch (Exception ex) {
            throw ex;
        }
        return json;
    }
*//*
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String exceptionHandler(Exception e) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        stringBuilder.append(e.getClass().getSimpleName() + e.getMessage() + "<br>");
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            stringBuilder.append(stackTraceElement.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    @ExceptionHandler(NoStartingPredicateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String noStartingPredicateExceptionHandler(NoStartingPredicateException e) throws IOException {
        return e.getMessage();
    }

    @ExceptionHandler(MyValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String myValidationExceptionHandler(MyValidationException e) throws IOException {
        return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String inferencePage(ModelMap model) {
        return "inference";
    }*/
}