package us.gonet.jxiserver.model;

public class CashUnitCounters {

    private String type;
    private int number;
    private long initialCount;
    private int dispensedCount;//SALIO DE LA CASETERA
    private int presentedCount;//TOTAL QUE LLEGO AL USUARIO
    private int retractedCount;//
    private int rejectedCount;//NO ALCANZO A SALIR
    private String status;
    private String bills;

    public int getRejectedCount() {
        return rejectedCount;
    }

    public void setRejectedCount(int rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getInitialCount() {
        return initialCount;
    }

    public void setInitialCount(long initialCount) {
        this.initialCount = initialCount;
    }

    public int getDispensedCount() {
        return dispensedCount;
    }

    public void setDispensedCount(int dispensedCount) {
        this.dispensedCount = dispensedCount;
    }

    public int getPresentedCount() {
        return presentedCount;
    }

    public void setPresentedCount(int presentedCount) {
        this.presentedCount = presentedCount;
    }

    public int getRetractedCount() {
        return retractedCount;
    }

    public void setRetractedCount(int retractedCount) {
        this.retractedCount = retractedCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBills() {
        return bills;
    }

    public void setBills(String bills) {
        this.bills = bills;
    }
}