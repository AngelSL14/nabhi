package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;

@Entity @Table(name = "TBL_AUDIT_LOGS")
public class AuditLogsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAudit;

    private String message;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_NODE_PROSA_ID"))
    private NodeProsaEntity node;

    public int getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(int idAudit) {
        this.idAudit = idAudit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NodeProsaEntity getNode() {
        return node;
    }

    public void setNode(NodeProsaEntity node) {
        this.node = node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditLogsEntity that = (AuditLogsEntity) o;

        if (idAudit != that.idAudit) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return node != null ? node.equals(that.node) : that.node == null;
    }

    @Override
    public int hashCode() {
        int result = idAudit;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (node != null ? node.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuditLogsEntity{" +
                "idAudit=" + idAudit +
                ", message='" + message + '\'' +
                ", node=" + node +
                '}';
    }
}
