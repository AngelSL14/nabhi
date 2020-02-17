package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity @Table(name = "TBL_DEVICE_NOTIF")
public class DeviceNotifEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID_DEVICE_NOTIF")
    private int idDeviceNotif;

    private String fkAtmTerminalId;
    public Timestamp getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Timestamp writeDate) {
        this.writeDate = writeDate;
    }

    @Column(length = 30)
    private String message;

    private Timestamp writeDate;

    public int getIdDeviceNotif() {
        return idDeviceNotif;
    }

    public void setIdDeviceNotif(int idDeviceNotif) {
        this.idDeviceNotif = idDeviceNotif;
    }

    public String getFkAtmTerminalId() {
        return fkAtmTerminalId;
    }

    public void setFkAtmTerminalId(String fkAtmTerminalId) {
        this.fkAtmTerminalId = fkAtmTerminalId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceNotifEntity entity = (DeviceNotifEntity) o;

        if (idDeviceNotif != entity.idDeviceNotif) return false;
        if (fkAtmTerminalId != null ? !fkAtmTerminalId.equals(entity.fkAtmTerminalId) : entity.fkAtmTerminalId != null)
            return false;
        if (message != null ? !message.equals(entity.message) : entity.message != null) return false;
        return writeDate != null ? writeDate.equals(entity.writeDate) : entity.writeDate == null;
    }

    @Override
    public int hashCode() {
        int result = idDeviceNotif;
        result = 31 * result + (fkAtmTerminalId != null ? fkAtmTerminalId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (writeDate != null ? writeDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceNotifEntity{" +
                "idDeviceNotif=" + idDeviceNotif +
                ", fkAtmTerminalId='" + fkAtmTerminalId + '\'' +
                ", message='" + message + '\'' +
                ", writeDate=" + writeDate +
                '}';
    }
}
