package com.springapp.mvc.media;

public class AjaxWithLastValDTO extends AjaxDTO {
    private String lastVal;

    public AjaxWithLastValDTO() {
    }

    public AjaxWithLastValDTO(String message, String lastVal, Boolean hasError) {
        super(message, hasError);
        this.lastVal = lastVal;
    }

    public String getLastVal() {
        return lastVal;
    }

    public void setLastVal(String lastVal) {
        this.lastVal = lastVal;
    }
}
