package us.gonet.serverutils.model.jdb.lite;

public class ATDLite {

    private String terminalId;
    private String deviceType;
    private String sequence;
    private String sequenceNumber;
    private int county;
    private String idf;
    private int nodeProsa;

    public String getTerminalId () {
        return terminalId;
    }

    public void setTerminalId ( String terminalId ) {
        this.terminalId = terminalId;
    }

    public String getDeviceType () {
        return deviceType;
    }

    public void setDeviceType ( String deviceType ) {
        this.deviceType = deviceType;
    }

    public String getSequence () {
        return sequence;
    }

    public void setSequence ( String sequence ) {
        this.sequence = sequence;
    }

    public String getSequenceNumber () {
        return sequenceNumber;
    }

    public void setSequenceNumber ( String sequenceNumber ) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getCounty () {
        return county;
    }

    public void setCounty ( int county ) {
        this.county = county;
    }

    public String getIdf () {
        return idf;
    }

    public void setIdf ( String idf ) {
        this.idf = idf;
    }

    public int getNodeProsa () {
        return nodeProsa;
    }

    public void setNodeProsa ( int nodeProsa ) {
        this.nodeProsa = nodeProsa;
    }

    @Override
    public String toString () {
        return "ATDLite{" +
                "terminalId='" + terminalId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", sequence='" + sequence + '\'' +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", county=" + county +
                ", idf='" + idf + '\'' +
                ", nodeProsa=" + nodeProsa +
                '}';
    }
}
