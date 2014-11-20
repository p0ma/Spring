package com.springapp.mvc.controllers.frozen;

public class ConfirmationDTO {
    private Boolean boolVal;
    private String message;

    public boolean isBoolVal() {
        return boolVal;
    }

    public void setBoolVal(Boolean boolVal) {
        this.boolVal = boolVal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
