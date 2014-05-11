package com.springapp.mvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import system.ModelContextConfiguration;

public class ModelContextProvider{
    private static ApplicationContext ctx = new AnnotationConfigApplicationContext(ModelContextConfiguration.class);
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
}