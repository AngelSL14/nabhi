package us.gonet.jxiserver.dbprosa.entity.lite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "TBL_COUNTY" )

public class CountyEntityLite {
    @Id
    @Column( name = "PK_COUNTY_CODE_ID" )
    private int countyCodeId;
    @Column( name = "COUNTY_CODE", length = 3 )
    private String countyCode;
    @Column( name = "COUNTY_NAME", length = 45 )
    private String countyName;
    @Column( name = "FK_STATE_CODE", length = 2)
    private String fkStateEntity;

    public int getCountyCodeId() {
        return countyCodeId;
    }

    public void setCountyCodeId(int countyCodeId) {
        this.countyCodeId = countyCodeId;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getFkStateEntity() {
        return fkStateEntity;
    }

    public void setFkStateEntity(String fkStateEntity) {
        this.fkStateEntity = fkStateEntity;
    }

    public CountyEntityLite() {
    }

    public CountyEntityLite(int countyCodeId, String countyCode, String countyName, String fkStateEntity) {
        this.countyCodeId = countyCodeId;
        this.countyCode = countyCode;
        this.countyName = countyName;
        this.fkStateEntity = fkStateEntity;
    }

    @Override
    public String toString() {
        return "CountyEntityLite{" +
                "countyCodeId=" + countyCodeId +
                ", countyCode='" + countyCode + '\'' +
                ", countyName='" + countyName + '\'' +
                ", fkStateEntity='" + fkStateEntity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountyEntityLite that = (CountyEntityLite) o;

        if (countyCodeId != that.countyCodeId) return false;
        if (countyCode != null ? !countyCode.equals(that.countyCode) : that.countyCode != null) return false;
        if (countyName != null ? !countyName.equals(that.countyName) : that.countyName != null) return false;
        return fkStateEntity != null ? fkStateEntity.equals(that.fkStateEntity) : that.fkStateEntity == null;
    }

    @Override
    public int hashCode() {
        int result = countyCodeId;
        result = 31 * result + (countyCode != null ? countyCode.hashCode() : 0);
        result = 31 * result + (countyName != null ? countyName.hashCode() : 0);
        result = 31 * result + (fkStateEntity != null ? fkStateEntity.hashCode() : 0);
        return result;
    }
}
