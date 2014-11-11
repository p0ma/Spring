package com.springapp.mvc.controllers;

public class NoStartingPredicateException extends Exception {
    public NoStartingPredicateException() {
        super("Inference parametersModel has no starting predicate. Please, choose one for that.");
    }
}
