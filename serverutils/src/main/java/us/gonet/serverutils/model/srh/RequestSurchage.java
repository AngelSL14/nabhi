package us.gonet.serverutils.model.srh;

import java.io.Serializable;


public class RequestSurchage implements Serializable {

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    @Override
    public String toString() {
        return "RequestSurchage{" +
                "track='" + track + '\'' +
                ", ip='" + ip + '\'' +
                ", transactionCode='" + transactionCode + '\'' +
                '}';
    }

    private String track;
    private String ip;
    private String transactionCode;

}
