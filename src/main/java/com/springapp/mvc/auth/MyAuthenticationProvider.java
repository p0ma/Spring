package com.springapp.mvc.auth;

import entities.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import repositories.exceptions.UserNotFoundException;
import service.UserService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider, AuthenticationManager {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            User user = userService.findByName(name);
            if (user.getPassword().equals(password)) {
                Authentication auth = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
                return auth;
            } else {
                throw new MyAuthenticationException();
            }
        } catch (UserNotFoundException e) {
            throw new MyAuthenticationException();
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
