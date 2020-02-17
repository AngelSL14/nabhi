package us.gonet.serverutils.model.jdb;

public class SurchargeModel {

    private String fiidAcquirer;
    private String fiidIssuing;
    private String id;
    private String surcharge;
    private String tranCode;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    @Override
    public String toString() {
        return "SurchargeModel{" +
                "fiidAcquirer='" + fiidAcquirer + '\'' +
                ", fiidIssuing='" + fiidIssuing + '\'' +
                ", id='" + id + '\'' +
                ", SurchargeModel='" + surcharge + '\'' +
                ", tranCode='" + tranCode + '\'' +
                '}';
    }
}
