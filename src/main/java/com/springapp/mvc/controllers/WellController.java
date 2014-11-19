package com.springapp.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import system.drilling.model.dto.PipeSectionDTO;
import system.drilling.model.well.MyValidationException;
import system.drilling.model.well.PipeSection;
import system.drilling.repositories.exceptions.NotFoundException;
import system.drilling.repositories.exceptions.WellNotFoundException;
import system.drilling.service.ParametersModelService;
import system.drilling.service.PipeSectionService;
import system.drilling.service.WellService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/well")
public class WellController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private WellService wellService;

    @Autowired
    private PipeSectionService pipeSectionService;

    @RequestMapping(value = "/add_pipe_section", consumes = MediaType.APPLICATION_JSON_VALUE)
     @ResponseBody
     public String addPipeSection(
            @Valid PipeSectionDTO pipeSectionDto
    ) throws IOException, Exception {
        try{
            wellService.addPipeSection(pipeSectionDto);
        } catch (Exception e) {
            throw e;
        }
        return "Pipe section has been added";
    }

    @RequestMapping(value = "/delete_pipe_section")
    @ResponseBody
    public String deletePipeSection(@RequestParam Long id) throws IOException, Exception {
        try{
            wellService.deletePipeSection(id);
        } catch (Exception e) {
            throw e;
        }
        return "Pipe section has been removed";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String processValidationError(MethodArgumentNotValidException e) {
        StringBuilder stringBuilder = new StringBuilder(500);
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for(FieldError fieldError : fieldErrors) {
            stringBuilder.append("Field " + fieldError.getField() + " has error with code " + fieldError.getCode());
        }
        return stringBuilder.toString();
    }

    @ExceptionHandler(MyValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String myValidationExceptionHandler(MyValidationException e) throws IOException {
        return e.getMessage();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String notFoundExceptionHandler(MyValidationException e) throws IOException {
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
        for(;iterator.hasNext();) {
            FieldError fieldError = iterator.next();
            stringBuilder.append("Value '" + fieldError.getRejectedValue() + "' for field " +
                    fieldError.getField() + " is not acceptable!");
            if(iterator.hasNext()) {
                stringBuilder.append("<br>");
            }
        }
        return stringBuilder.toString();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String validationExceptionHandler(ValidationException e) throws IOException {
        return "Validation Exception: ";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String missingServletRequestParameterExceptionHandler(Exception e) throws IOException {
        return "Missing parameters error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String exceptionHandler(Exception e) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        stringBuilder.append(e.getClass().getSimpleName() + e.getMessage() + "<br>");
        for(StackTraceElement stackTraceElement : stackTraceElements) {
            stringBuilder.append(stackTraceElement.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    @RequestMapping(value = "/reorder")
    @ResponseBody
    public String reorder(@RequestParam Long fromId, @RequestParam Long toId, Model model) throws NotFoundException {
        int[] newOrder = pipeSectionService.swapOrder(fromId, toId);
        return "Pipe sections " + newOrder[0] + " and " + newOrder[1] + " has been swapped";
    }

    @ExceptionHandler(WellNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String wellNotFoundError() throws IOException {
        return "Well not found";
    }

    @RequestMapping(value = "/add_pipe_section_form", method = RequestMethod.GET)
     public String addPipeSectionForm(ModelMap model) {

        return "add_pipe_section_form";
    }

    @RequestMapping(value = "/delete_pipe_section_form", method = RequestMethod.GET)
    public String deletePipeSectionForm(ModelMap model) {
        List<PipeSection> pipeSections = wellService.getWell().getPipeSections();
        model.addAttribute("pipeSections", pipeSections);
        return "delete_pipe_section_form";
    }

    @RequestMapping(value = "/pipe_sections", method = RequestMethod.GET)
    public String pipeSections(ModelMap model) {
        List<PipeSection> pipeSections = wellService.getWell().getPipeSections();
        model.addAttribute("pipeSections", pipeSections);
        return "pipe_sections";
    }

}