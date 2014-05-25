package com.springapp.mvc.controllers;

public class NoStartingPredicateException extends Exception {
    public NoStartingPredicateException() {
        super("Inference model has no starting predicate. Please, choose one for that.");
    }
}
