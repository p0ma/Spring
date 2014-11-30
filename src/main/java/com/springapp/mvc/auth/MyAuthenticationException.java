package com.springapp.mvc.auth;

import localization.LocalizationUtils;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by Damager1 on 30.11.2014.
 */
public class MyAuthenticationException extends AuthenticationException {
    public MyAuthenticationException() {
        super(LocalizationUtils.getMessage(MyAuthenticationException.class.getSimpleName() + ".message"));
    }
}
