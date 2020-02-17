package us.gonet.serverutils.model.jdb;

public class NodeProsa {

    private Integer idNode;
    private String ip;
    private String port;
    private int tracerNumber;
    private String nodeName;
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
    public String toString() {
        return "NodeProsa{" +
                "idNode=" + idNode +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", tracerNumber=" + tracerNumber +
                ", nodeName='" + nodeName + '\'' +
                ", zpk='" + zpk + '\'' +
                '}';
    }
}
