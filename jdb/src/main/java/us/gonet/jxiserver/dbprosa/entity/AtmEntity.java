package us.gonet.jxiserver.dbprosa.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "TBL_ATM")
public class AtmEntity implements Serializable{

    private static final long serialVersionUID = 2405172041980251807L;

    @Id @Column(name = "PK_TERMINAL_ID", length = 16)
    private String id;

    @Column(length = 15)
    private String ip;

    @Lob
    private String lastTrx;

    private boolean activeTrx;

    @Column(length = 25)
    private String sucursal;

    @Lob
    private String receipt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLastTrx() {
        return lastTrx;
    }

    public void setLastTrx(String lastTrx) {
        this.lastTrx = lastTrx;
    }

    public boolean isActiveTrx() {
        return activeTrx;
    }

    public void setActiveTrx(boolean activeTrx) {
        this.activeTrx = activeTrx;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtmEntity atmEntity = (AtmEntity) o;

        if (activeTrx != atmEntity.activeTrx) return false;
        if (id != null ? !id.equals(atmEntity.id) : atmEntity.id != null) return false;
        if (ip != null ? !ip.equals(atmEntity.ip) : atmEntity.ip != null) return false;
        if (lastTrx != null ? !lastTrx.equals(atmEntity.lastTrx) : atmEntity.lastTrx != null) return false;
        if (sucursal != null ? !sucursal.equals(atmEntity.sucursal) : atmEntity.sucursal != null) return false;
        return receipt != null ? receipt.equals(atmEntity.receipt) : atmEntity.receipt == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (lastTrx != null ? lastTrx.hashCode() : 0);
        result = 31 * result + (activeTrx ? 1 : 0);
        result = 31 * result + (sucursal != null ? sucursal.hashCode() : 0);
        result = 31 * result + (receipt != null ? receipt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AtmEntity{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", lastTrx='" + lastTrx + '\'' +
                ", activeTrx=" + activeTrx +
                ", sucursal='" + sucursal + '\'' +
                ", receipt='" + receipt + '\'' +
                '}';
    }
}
