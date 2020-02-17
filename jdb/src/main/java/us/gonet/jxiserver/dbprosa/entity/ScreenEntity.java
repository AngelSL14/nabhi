package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "TBL_SCREEN")
public class ScreenEntity implements Serializable{

    private static final long serialVersionUID = 2405172041950251807L;
    @Id
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "PK_ATM_TERMINAL_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ATM_SCREEN"))
    private AtmEntity atm;

    @Column(length = 10)
    private String screenType;

    @Column(length = 5)
    private String extPublicity;

    @Column(length = 15)
    private String namePublicity;

    public ScreenEntity() {
    }

    public ScreenEntity(AtmEntity atm, String screenType, String extPublicity, String namePublicity) {
        this.atm = atm;
        this.screenType = screenType;
        this.extPublicity = extPublicity;
        this.namePublicity = namePublicity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AtmEntity getAtm() {
        return atm;
    }

    public void setAtm(AtmEntity atm) {
        this.atm = atm;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getExtPublicity() {
        return extPublicity;
    }

    public void setExtPublicity(String extPublicity) {
        this.extPublicity = extPublicity;
    }

    public String getNamePublicity() {
        return namePublicity;
    }

    public void setNamePublicity(String namePublicity) {
        this.namePublicity = namePublicity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ScreenEntity entity = (ScreenEntity) o;

        if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
        if (atm != null ? !atm.equals(entity.atm) : entity.atm != null) return false;
        if (screenType != null ? !screenType.equals(entity.screenType) : entity.screenType != null) return false;
        if (extPublicity != null ? !extPublicity.equals(entity.extPublicity) : entity.extPublicity != null)
            return false;
        return namePublicity != null ? namePublicity.equals(entity.namePublicity) : entity.namePublicity == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (atm != null ? atm.hashCode() : 0);
        result = 31 * result + (screenType != null ? screenType.hashCode() : 0);
        result = 31 * result + (extPublicity != null ? extPublicity.hashCode() : 0);
        result = 31 * result + (namePublicity != null ? namePublicity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScreenEntity{" +
                "id='" + id + '\'' +
                ", atm=" + atm +
                ", screenType='" + screenType + '\'' +
                ", extPublicity='" + extPublicity + '\'' +
                ", namePublicity='" + namePublicity + '\'' +
                '}';
    }
}
