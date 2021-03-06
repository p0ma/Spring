package com.springapp.mvc.controllers;

import entities.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@RequestMapping("/login")
@PreAuthorize("not isAuthenticated()")
public class LoginController {
    @Autowired
    private AuthenticationProvider myAuthenticationProvider;

    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute(new User());
        return "auth/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signIn(@RequestParam(required = false) String logout, ModelMap model, @Valid User user,
                         BindingResult bindingResult) {
        if (logout != null) {
            return "redirect:";
        }
        if (bindingResult.hasErrors()) {
            model.put(BindingResult.class.getName() + ".user", bindingResult);
            return "auth/login";
        }
        try {
            Authentication authentication =
                    myAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                            user, user.getPassword(), user.getAuthorities()));
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return "basic/hello";
            } else {
                return "auth/login";
            }
        } catch (AuthenticationException e) {
            bindingResult.rejectValue("password", "error.user", e.getMessage());
            return "auth/login";
        }
    }
}
