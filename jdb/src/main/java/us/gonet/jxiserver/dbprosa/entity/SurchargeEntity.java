package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "TBL_SURCHARGE")
public class SurchargeEntity {
    @Id
    private int id;
    private String fiidAcquirer;
    private String fiidIssuing;
    private String tranCode;
    private String surcharge;

    public SurchargeEntity() {
    }

    public SurchargeEntity(int id, String fiidAcquirer, String fiidIssuing, String tranCode, String surcharge) {
        this.id = id;
        this.fiidAcquirer = fiidAcquirer;
        this.fiidIssuing = fiidIssuing;
        this.tranCode = tranCode;
        this.surcharge = surcharge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFiidAcquirer() {
        return fiidAcquirer;
    }

    public void setFiidAcquirer(String fiidAcquirer) {
        this.fiidAcquirer = fiidAcquirer;
    }

    public String getFiidIssuing() {
        return fiidIssuing;
    }

    public void setFiidIssuing(String fiidIssuing) {
        this.fiidIssuing = fiidIssuing;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SurchargeEntity that = (SurchargeEntity) o;

        if (id != that.id) return false;
        if (fiidAcquirer != null ? !fiidAcquirer.equals(that.fiidAcquirer) : that.fiidAcquirer != null) return false;
        if (fiidIssuing != null ? !fiidIssuing.equals(that.fiidIssuing) : that.fiidIssuing != null) return false;
        if (tranCode != null ? !tranCode.equals(that.tranCode) : that.tranCode != null) return false;
        return surcharge != null ? surcharge.equals(that.surcharge) : that.surcharge == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (fiidAcquirer != null ? fiidAcquirer.hashCode() : 0);
        result = 31 * result + (fiidIssuing != null ? fiidIssuing.hashCode() : 0);
        result = 31 * result + (tranCode != null ? tranCode.hashCode() : 0);
        result = 31 * result + (surcharge != null ? surcharge.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SurchargeEntity{" +
                "id=" + id +
                ", fiidAcquirer='" + fiidAcquirer + '\'' +
                ", fiidIssuing='" + fiidIssuing + '\'' +
                ", tranCode='" + tranCode + '\'' +
                ", surcharge='" + surcharge + '\'' +
                '}';
    }
}
