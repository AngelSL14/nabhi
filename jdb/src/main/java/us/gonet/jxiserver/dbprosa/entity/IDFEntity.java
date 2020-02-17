package us.gonet.jxiserver.dbprosa.entity;

import com.google.gson.GsonBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "TBL_IDF" )

public class IDFEntity implements Serializable {

    @Id
    @Column( name = "PK_FIID", length = 4 )
    private String fiid;
    @Column( name = "LOGICAL_NET", length = 4 )
    private String logicalNet;
    @Column( name = "NAME", length = 45 )
    private String name;
    @Column( name = "ACQUIRING_ID", length = 11 )
    private String acquiringId;
    @Column( name = "NAME_LONG", length = 150 )
    private String nameLong;

    @ManyToOne( optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn( name = "FK_COUNTRY_CODE", foreignKey = @ForeignKey( name = "FK_COUNTRY_CODE" ) )
    @Fetch( FetchMode.JOIN )
    private CountryEntity country;

    public IDFEntity() {
    }
    private static final long serialVersionUID = 2405172041950251807L;



    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public IDFEntity(String fiid, String logicalNet, String name, String acquiringId, String nameLong, CountryEntity country) {
        this.fiid = fiid;
        this.logicalNet = logicalNet;
        this.name = name;
        this.acquiringId = acquiringId;
        this.nameLong = nameLong;
        this.country = country;
    }

    public String getFiid() {
        return fiid;
    }

    public void setFiid(String fiid) {
        this.fiid = fiid;
    }

    public String getLogicalNet() {
        return logicalNet;
    }

    public void setLogicalNet(String logicalNet) {
        this.logicalNet = logicalNet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcquiringId() {
        return acquiringId;
    }

    public void setAcquiringId(String acquiringId) {
        this.acquiringId = acquiringId;
    }

    public String getNameLong() {
        return nameLong;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IDFEntity idfEntity = (IDFEntity) o;

        if (fiid != null ? !fiid.equals(idfEntity.fiid) : idfEntity.fiid != null) return false;
        if (logicalNet != null ? !logicalNet.equals(idfEntity.logicalNet) : idfEntity.logicalNet != null) return false;
        if (name != null ? !name.equals(idfEntity.name) : idfEntity.name != null) return false;
        if (acquiringId != null ? !acquiringId.equals(idfEntity.acquiringId) : idfEntity.acquiringId != null)
            return false;
        if (nameLong != null ? !nameLong.equals(idfEntity.nameLong) : idfEntity.nameLong != null) return false;
        return country != null ? country.equals(idfEntity.country) : idfEntity.country == null;
    }

    @Override
    public int hashCode() {
        int result = fiid != null ? fiid.hashCode() : 0;
        result = 31 * result + (logicalNet != null ? logicalNet.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (acquiringId != null ? acquiringId.hashCode() : 0);
        result = 31 * result + (nameLong != null ? nameLong.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
