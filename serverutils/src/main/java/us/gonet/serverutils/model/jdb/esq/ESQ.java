package us.gonet.serverutils.model.jdb.esq;

import us.gonet.serverutils.model.jdb.County;

import java.util.List;

public class ESQ {

    private String terminalId;
    private String ip;
    private String fiid;
    private County county;
    private List< DeviceSingle > devices;
    private List< Journal > journal;

    public String getFiid() {
        return fiid;
    }

    public void setFiid(String fiid) {
        this.fiid = fiid;
    }

    public String getTerminalId () {
        return terminalId;
    }

    public void setTerminalId ( String terminalId ) {
        this.terminalId = terminalId;
    }



    public String getIp () {
        return ip;
    }

    public void setIp ( String ip ) {
        this.ip = ip;
    }

    public County getCounty () {
        return county;
    }

    public void setCounty ( County county ) {
        this.county = county;
    }

    public List< DeviceSingle > getDevices () {
        return devices;
    }

    public void setDevices ( List< DeviceSingle > devices ) {
        this.devices = devices;
    }

    public List< Journal > getJournal () {
        return journal;
    }

    public void setJournal ( List< Journal > journal ) {
        this.journal = journal;
    }

    @Override
    public String toString () {
        return "ESQ{" +
                "terminalId='" + terminalId + '\'' +
                ", ip='" + ip + '\'' +
                ", county=" + county +
                ", devices=" + devices +
                ", journal=" + journal +
                '}';
    }
}
