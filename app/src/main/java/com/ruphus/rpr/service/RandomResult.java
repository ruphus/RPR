package com.ruphus.rpr.service;

import java.util.Date;

public class RandomResult
{
    private Date completionTime;
    private boolean data;
    private String errorMessage;

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public boolean getData() {
        return data;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}