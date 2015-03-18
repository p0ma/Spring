package com.springapp.mvc.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationManager implements AuthenticationManager {

    @Autowired
    private AuthenticationProvider myAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return myAuthenticationProvider.authenticate(authentication);
    }
}
