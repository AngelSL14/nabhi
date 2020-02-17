package us.gonet.jxiserver.model.request;

import java.util.Map;

public class Generic
{
    private String termId;
    private String ip;
    private String txCommission;
    private String tipoCuenta;
    private String nip;
    private String track;
    private Map<String, String> emv;

    public Generic()
    {
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTxCommission() {
        return txCommission;
    }

    public void setTxCommission(String txCommission) {
        this.txCommission = txCommission;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public Map<String, String> getEmv() {
        return emv;
    }

    public void setEmv(Map<String, String> emv) {
        this.emv = emv;
    }


    public static GenericBuilder builder()
    {
        return new GenericBuilder();
    }

    protected Generic(String termId, String ip, String txCommission, String tipoCuenta, String nip, String track, Map<String, String> emv) {
        this.termId = termId;
        this.ip = ip;
        this.txCommission = txCommission;
        this.tipoCuenta = tipoCuenta;
        this.nip = nip;
        this.track = track;
        this.emv = emv;
    }


    @Override
    public String toString() {
        return "Generic{" +
                "termId='" + termId + '\'' +
                ", ip='" + ip + '\'' +
                ", txCommission='" + txCommission + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", nip='" + nip + '\'' +
                ", track='" + track + '\'' +
                ", emv=" + emv +
                '}';
    }

}