package com.example.srh.models;

public class ResponseSurchage {

    private String fiidAcquirer;
    private String fiidIssuing;
    private String tranCode;
    private String surcharge;

    public String getFiidAcquirer() {
        return fiidAcquirer;
    }

    public void setFiidAcquirer(String fiidAcquirer) {
        this.fiidAcquirer = fiidAcquirer;
    }

    public String getFiidIssuing() {
        return fiidIssuing;
    }

    public void setFiidIssuing(String fiidIssuing) {
        this.fiidIssuing = fiidIssuing;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    @Override
    public String toString() {
        return "ResponseSurchage{" +
                "fiidAcquirer='" + fiidAcquirer + '\'' +
                ", fiidIssuing='" + fiidIssuing + '\'' +
                ", tranCode='" + tranCode + '\'' +
                ", surcharge='" + surcharge + '\'' +
                '}';
    }
}
