package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "TBL_DEVICE_STATUS")
public class DeviceStatusEntity implements Serializable
{
    private static final long serialVersionUID = 1934122041950251207L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID_DEVICE_STATUS")
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_ATM_TERMINAL_ID", foreignKey = @ForeignKey(name = "FK_ATM_DEVICE_STATUS"))
    private AtmEntity atm;

    @Column(length = 10)
    private String status;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_DEVICE_ID", foreignKey = @ForeignKey(name = "FK_DEVICE_DEVICE_STATUS"))
    private DeviceEntity device;

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AtmEntity getAtm() {
        return atm;
    }

    public void setAtm(AtmEntity atm) {
        this.atm = atm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceStatusEntity that = (DeviceStatusEntity) o;

        if (id != that.id) return false;
        if (atm != null ? !atm.equals(that.atm) : that.atm != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return device != null ? device.equals(that.device) : that.device == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (atm != null ? atm.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (device != null ? device.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceStatusEntity{" +
                "id=" + id +
                ", atm=" + atm +
                ", status='" + status + '\'' +
                ", device=" + device +
                '}';
    }
}
