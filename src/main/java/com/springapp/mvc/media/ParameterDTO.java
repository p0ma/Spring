package com.springapp.mvc.media;

/**
 * Created with IntelliJ IDEA.
 * User: Damager1
 * Date: 16.11.14
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public class ParameterDTO {
    private String name;
    private String val;

    public ParameterDTO() {
    }

    public ParameterDTO(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
