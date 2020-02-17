package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TBL_DEVICE")

public class DeviceEntity implements Serializable{
    private static final long serialVersionUID = 1905122041950251207L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID_DEVICE")
    private int id;

    private String type;

    public DeviceEntity() {
    }

    public DeviceEntity(String type) {
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEntity that = (DeviceEntity) o;

        if (id != that.id) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
