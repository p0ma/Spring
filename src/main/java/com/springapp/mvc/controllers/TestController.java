package com.springapp.mvc.controllers;

import com.springapp.mvc.media.UserRegistrationDTO;
import entities.auth.User;
import entities.auth.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

@Controller
@RequestMapping("/test1")
public class TestController {

    //private static final String MODEL_CONFIG_PATH = "classpath*:system/drilling/parametersModel/config.xml";

    @Autowired
    private AuthenticationProvider myAuthenticationProvider;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String test1(@AuthenticationPrincipal User user, ModelMap model) {
        String s = "parameters";
        if (user == null) {
            // s = fastLogin();
        }
        return s;
    }

    private String fastLogin() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setName("user");
        userRegistrationDTO.setEmail("user@gmail.com");
        userRegistrationDTO.setPassword("1234");
        User user = UserFactory.getUser(userRegistrationDTO);
        userService.create(user);
        Authentication authentication =
                myAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                        user, user.getPassword()));
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "hello";
        } else {
            return "hello";
        }
    }
}