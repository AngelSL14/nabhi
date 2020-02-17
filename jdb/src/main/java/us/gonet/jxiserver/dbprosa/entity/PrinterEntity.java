package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "TBL_PRINTER_DEVICE")
public class PrinterEntity implements Serializable {

    private static final long serialVersionUID = 2405172044950251807L;

    @Id
    private int pkIdDevice;

    @OneToOne
    @MapsId
    @JoinColumn(name = "PK_ID_DEVICE", nullable = false, foreignKey = @ForeignKey(name = "FK_DEVICE_PRINTER"))
    private DeviceStatusEntity device;

    @Column(length = 10)
    private String paper;

    @Column(length = 10)
    private String toner;


    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public int getPkIdDevice() {
        return pkIdDevice;
    }

    public void setPkIdDevice(int pkIdDevice) {
        this.pkIdDevice = pkIdDevice;
    }

    public DeviceStatusEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceStatusEntity device) {
        this.device = device;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getToner() {
        return toner;
    }

    public void setToner(String toner) {
        this.toner = toner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PrinterEntity that = (PrinterEntity) o;

        if (pkIdDevice != that.pkIdDevice) return false;
        if (device != null ? !device.equals(that.device) : that.device != null) return false;
        if (paper != null ? !paper.equals(that.paper) : that.paper != null) return false;
        return toner != null ? toner.equals(that.toner) : that.toner == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + pkIdDevice;
        result = 31 * result + (device != null ? device.hashCode() : 0);
        result = 31 * result + (paper != null ? paper.hashCode() : 0);
        result = 31 * result + (toner != null ? toner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PrinterEntity{" +
                "pkIdDevice=" + pkIdDevice +
                ", device=" + device +
                ", paper='" + paper + '\'' +
                ", toner='" + toner + '\'' +
                '}';
    }
}
