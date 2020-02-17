package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "TBL_NODE_PROSA")
public class NodeProsaEntity implements Serializable{

    @Id @Column(name = "PK_ID_NODE", length = 5)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer idNode;

    @Column(length = 15)
    private String ip;

    @Column(length = 6)
    private String port;

    private int tracerNumber;

    @Column(length = 10)
    private String nodeName;
    private static final long serialVersionUID = 2405102041950251807L;



    public static long getSerialVersionUID() {

        return serialVersionUID;
    }
    @Column(length = 32)
    private String zpk;

    public Integer getIdNode() {
        return idNode;
    }

    public void setIdNode(Integer idNode) {
        this.idNode = idNode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getTracerNumber() {
        return tracerNumber;
    }

    public void setTracerNumber(int tracerNumber) {
        this.tracerNumber = tracerNumber;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getZpk() {
        return zpk;
    }

    public void setZpk(String zpk) {
        this.zpk = zpk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeProsaEntity entity = (NodeProsaEntity) o;

        if (tracerNumber != entity.tracerNumber) return false;
        if (idNode != null ? !idNode.equals(entity.idNode) : entity.idNode != null) return false;
        if (ip != null ? !ip.equals(entity.ip) : entity.ip != null) return false;
        if (port != null ? !port.equals(entity.port) : entity.port != null) return false;
        if (nodeName != null ? !nodeName.equals(entity.nodeName) : entity.nodeName != null) return false;
        return zpk != null ? zpk.equals(entity.zpk) : entity.zpk == null;
    }

    @Override
    public int hashCode() {
        int result = idNode != null ? idNode.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + tracerNumber;
        result = 31 * result + (nodeName != null ? nodeName.hashCode() : 0);
        result = 31 * result + (zpk != null ? zpk.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NodeProsaEntity{" +
                "idNode=" + idNode +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", tracerNumber=" + tracerNumber +
                ", nodeName='" + nodeName + '\'' +
                ", zpk='" + zpk + '\'' +
                '}';
    }
}
