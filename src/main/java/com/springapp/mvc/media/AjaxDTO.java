package com.springapp.mvc.media;

public class AjaxDTO {
    private String message;
    private boolean hasError;

    public AjaxDTO() {
    }

    public AjaxDTO(String message, boolean hasError) {
        this.message = message;
        this.hasError = hasError;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
