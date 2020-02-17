package us.gonet.jxiserver.dbprosa.entity;

import com.google.gson.GsonBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "TBL_ATM_ATD" )
public class ATDEntity implements Serializable{

    private static final long serialVersionUID = 2405172041950257507L;



    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    @Id
    private String terminalId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "PK_ATM_TERMINAL_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ATM_ATD"))
    private AtmEntity atm;

    @Column( name = "SEQUENCE_NUMBER", length = 12 )
    private String sequenceNumber;

    @Column( name = "DEVICE_TYPE", length = 2 )
    private String deviceType;

    @ManyToOne( optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn( name = "FK_FIID", foreignKey = @ForeignKey( name = "FK_FIID" ) )
    @Fetch( FetchMode.JOIN )
    private IDFEntity idf;

    @ManyToOne( optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn( name = "FK_COUNTY_CODE_ID", foreignKey = @ForeignKey( name = "FK_ATM_ATD_COUNTY" ) )
    @Fetch( FetchMode.JOIN )
    private CountyEntity county;

    @ManyToOne( optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn( name = "FK_NODE_PROSA_ID", foreignKey = @ForeignKey( name = "FK_ATM_ATD_NODE_PROSA" ) )
    @Fetch( FetchMode.JOIN )
    private NodeProsaEntity nodeProsa;

    @Column( name = "SEQUENCE", length = 9 )
    private String sequence;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public AtmEntity getAtm() {
        return atm;
    }

    public void setAtm(AtmEntity atm) {
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

    public IDFEntity getIdf() {
        return idf;
    }

    public void setIdf(IDFEntity idf) {
        this.idf = idf;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }

    public NodeProsaEntity getNodeProsa() {
        return nodeProsa;
    }

    public void setNodeProsa(NodeProsaEntity nodeProsa) {
        this.nodeProsa = nodeProsa;
    }



    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }

}
