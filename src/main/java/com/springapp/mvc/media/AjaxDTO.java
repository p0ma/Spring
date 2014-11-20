package com.springapp.mvc.media;

public class AjaxDTO {
    private String message;
    private String lastVal;
    private Boolean hasError;

    public AjaxDTO() {
    }

    public AjaxDTO(String message, String lastVal, Boolean hasError) {
        this.message = message;
        this.lastVal = lastVal;
        this.hasError = hasError;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastVal() {
        return lastVal;
    }

    public void setLastVal(String lastVal) {
        this.lastVal = lastVal;
    }
}
