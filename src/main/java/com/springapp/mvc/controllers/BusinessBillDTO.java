package com.springapp.mvc.controllers;

import java.util.Date;

public class BusinessBillDTO {

    private String billDate;
    private String billReference;

    public BusinessBillDTO() {

    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillReference() {
        return billReference;
    }

    public void setBillReference(String billReference) {
        this.billReference = billReference;
    }
}
