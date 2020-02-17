package us.gonet.serverutils.model.jdb;

public class ATD {

    private String terminalId;
    private Atm atm;
    private String sequence;
    private String sequenceNumber;
    private String deviceType;
    private IDF idf;
    private County county;
    private NodeProsa nodeProsa;


    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public IDF getIdf() {
        return idf;
    }

    public void setIdf(IDF idf) {
        this.idf = idf;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public NodeProsa getNodeProsa() {
        return nodeProsa;
    }

    public void setNodeProsa(NodeProsa nodeProsa) {
        this.nodeProsa = nodeProsa;
    }

    @Override
    public String toString() {
        return "ATD{" +
                "terminalId='" + terminalId + '\'' +
                ", atm=" + atm +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", idf=" + idf +
                ", county=" + county +
                ", nodeProsa=" + nodeProsa +
                '}';
    }
}
