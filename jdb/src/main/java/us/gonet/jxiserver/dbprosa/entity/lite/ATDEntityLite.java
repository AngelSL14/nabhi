package us.gonet.jxiserver.dbprosa.entity.lite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ATM_ATD")
public class ATDEntityLite {

    @Id
    @Column(name = "PK_ATM_TERMINAL_ID", length = 16, nullable = false)
    private String terminalId;
    @Column( name = "SEQUENCE", length = 9 )
    private String sequence;
    @Column( name = "SEQUENCE_NUMBER", length = 12 )
    private String sequenceNumber;

    @Column( name = "DEVICE_TYPE", length = 2 )
    private String deviceType;

    @Column( name = "FK_FIID", length = 4)
    private String idf;

    @Column( name = "FK_COUNTY_CODE_ID" )
    private int county;

    @Column( name = "FK_NODE_PROSA_ID" )
    private int nodeProsa;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
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

    public String getIdf() {
        return idf;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setIdf(String idf) {
        this.idf = idf;
    }

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public int getNodeProsa() {
        return nodeProsa;
    }

    public void setNodeProsa(int nodeProsa) {
        this.nodeProsa = nodeProsa;
    }

    @Override
    public String toString() {
        return "ATDEntityLite{" +
                "terminalId='" + terminalId + '\'' +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", idf='" + idf + '\'' +
                ", county=" + county +
                ", nodeProsa=" + nodeProsa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ATDEntityLite that = (ATDEntityLite) o;

        if (county != that.county) return false;
        if (nodeProsa != that.nodeProsa) return false;
        if (terminalId != null ? !terminalId.equals(that.terminalId) : that.terminalId != null) return false;
        if (sequenceNumber != null ? !sequenceNumber.equals(that.sequenceNumber) : that.sequenceNumber != null)
            return false;
        if (deviceType != null ? !deviceType.equals(that.deviceType) : that.deviceType != null) return false;
        return idf != null ? idf.equals(that.idf) : that.idf == null;
    }

    @Override
    public int hashCode() {
        int result = terminalId != null ? terminalId.hashCode() : 0;
        result = 31 * result + (sequenceNumber != null ? sequenceNumber.hashCode() : 0);
        result = 31 * result + (deviceType != null ? deviceType.hashCode() : 0);
        result = 31 * result + (idf != null ? idf.hashCode() : 0);
        result = 31 * result + county;
        result = 31 * result + nodeProsa;
        return result;
    }


}
