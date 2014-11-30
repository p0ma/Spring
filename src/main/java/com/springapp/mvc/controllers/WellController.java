package com.springapp.mvc.controllers;

import com.springapp.mvc.media.ReorderDTO;
import entities.auth.User;
import entities.drilling.model.dto.PipeSectionDTO;
import entities.drilling.model.well.MyValidationException;
import entities.drilling.model.well.PipeSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import repositories.exceptions.PipeSectionNotFoundException;
import repositories.exceptions.UserNotFoundException;
import repositories.exceptions.WellNotFoundException;
import service.PipeSectionService;
import service.WellService;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/well")
public class WellController {

    @Autowired
    private WellService wellService;

    @Autowired
    private PipeSectionService pipeSectionService;

    @RequestMapping(value = "/add_pipe_section", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addPipeSection(@AuthenticationPrincipal User user, @RequestBody PipeSectionDTO pipeSectionDTO) {
        try {
            wellService.addPipeSection(user, pipeSectionDTO);
            return "Pipe section has been added";
        } catch (MyValidationException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/delete_pipe_section", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletePipeSection(@AuthenticationPrincipal User user, @RequestBody Long id) {
        try {
            wellService.removePipeSection(user, id);
            return "Pipe section has been removed";
        } catch (PipeSectionNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/reorder", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String reorder(@AuthenticationPrincipal User user, @RequestBody ReorderDTO reorderDTO) {
        int[] newOrder = null;
        try {
            newOrder = wellService.reorderPipes(user, reorderDTO.getFromId(), reorderDTO.getToId());
        } catch (WellNotFoundException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (newOrder != null) {
            return "Pipe sections " + newOrder[0] + " and " + newOrder[1] + " has been swapped";
        } else {
            return "Pipe sections hasn't been reordered due to internal error";
        }
    }

    @RequestMapping(value = "/add_pipe_section_form", method = RequestMethod.GET)
    public String addPipeSectionForm() {
        return "add_pipe_section_form";
    }

    @RequestMapping(value = "/delete_pipe_section_form", method = RequestMethod.GET)
    public String deletePipeSectionForm(@AuthenticationPrincipal User user, ModelMap model) {
        List<PipeSection> pipeSections = null;
        try {
            pipeSections = pipeSectionService.getPipeSections(user);
            model.addAttribute("pipeSections", pipeSections);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "delete_pipe_section_form";
    }

    @RequestMapping(value = "/pipe_sections", method = RequestMethod.GET)
    public String pipeSections(@AuthenticationPrincipal User user, ModelMap model) {
        List<PipeSection> pipeSections = null;
        try {
            pipeSections = pipeSectionService.getPipeSections(user);
            model.addAttribute("pipeSections", pipeSections);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "pipe_sections";
    }

}