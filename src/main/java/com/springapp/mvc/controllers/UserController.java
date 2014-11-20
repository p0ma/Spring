package com.springapp.mvc.controllers;

import com.springapp.mvc.media.UserRegistrationDTO;
import entities.auth.User;
import entities.auth.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationProvider myAuthenticationProvider;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(ModelMap model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.put(BindingResult.class.getName() + ".user", bindingResult);
            return "login";
        }
        Authentication authentication =
                myAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                        user, user.getPassword()));
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/welcome";
        } else {
            bindingResult.rejectValue("password", "error.user", "No such user or password matching");
            return "login";
        }
    }

    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "registration";
    }

    @RequestMapping("/logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(ModelMap model, @Valid UserRegistrationDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.put(BindingResult.class.getName() + ".user", bindingResult);
            return "registration";
        } else {
            userService.create(UserFactory.getUser(user));
        }
        return "redirect:/welcome";
    }
}
