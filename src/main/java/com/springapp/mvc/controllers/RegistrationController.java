package com.springapp.mvc.controllers;

import com.springapp.mvc.media.UserRegistrationDTO;
import entities.auth.User;
import entities.auth.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repositories.exceptions.UserAlreadyExistsException;
import repositories.exceptions.UserMailAlreadyUsedException;
import service.UserService;

import javax.validation.Valid;


@Controller
@PreAuthorize("not isAuthenticated()")
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationProvider myAuthenticationProvider;


    @RequestMapping(method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute(new UserRegistrationDTO());
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registration(ModelMap model, @Valid UserRegistrationDTO userRegistrationDTO,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.put(BindingResult.class.getName() + ".user", bindingResult);
            return "registration";
        } else {
            try {
                User user = userService.register(UserFactory.getUser(userRegistrationDTO));
                Authentication authentication =
                        myAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                                user, user.getPassword()));
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    return "redirect:/welcome";
                } else {
                    return "redirect:/login";
                }
            } catch (UserAlreadyExistsException e) {
                bindingResult.rejectValue("name", "error.userRegistrationDTO", e.getMessage());

                return "registration";
            } catch (UserMailAlreadyUsedException e) {
                bindingResult.rejectValue("email", "error.userRegistrationDTO", e.getMessage());
                return "registration";
            }
        }
    }
}
