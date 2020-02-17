package com.example.srh.exeptionsutils;

public class ErrorWS {

    private String cause;
    private String errorMessage;

    public ErrorWS () {

    }

    public ErrorWS ( String cause, String errorMessage ) {
        this.cause = cause;
        this.errorMessage = errorMessage;
    }

    public String getCause () {
        return cause;
    }

    public void setCause ( String cause ) {
        this.cause = cause;
    }

    public String getErrorMessage () {
        return errorMessage;
    }

    public void setErrorMessage ( String errorMessage ) {
        this.errorMessage = errorMessage;
    }
}
