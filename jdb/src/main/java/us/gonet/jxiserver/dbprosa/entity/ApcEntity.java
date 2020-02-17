package us.gonet.jxiserver.dbprosa.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table( name = "TBL_APC" )
public class ApcEntity {

    @Id
    @Column( name = "PK_KEY", length = 8 )
    private String key;


    @ManyToOne( optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn( name = "FK_FIID", foreignKey = @ForeignKey( name = "FK_FIID_APC" ) )
    @Fetch( FetchMode.JOIN )
    private IDFEntity idf;

    @Column( name = "SHARING_GROUP", length = 24 )
    private String sharingGroup;

    @ManyToOne( optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn( name = "FK_ALLOWED_CODE", foreignKey = @ForeignKey( name = "FK_ALLOWED_CODE" ) )
    @Fetch( FetchMode.JOIN )
    private TranAllowedEntity allowedCode;

    @Column( name = "ROUTING_GROUP", length = 11 )
    private String rountingGroup;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public IDFEntity getIdf() {
        return idf;
    }

    public void setIdf(IDFEntity idf) {
        this.idf = idf;
    }

    public String getSharingGroup() {
        return sharingGroup;
    }

    public void setSharingGroup(String sharingGroup) {
        this.sharingGroup = sharingGroup;
    }

    public TranAllowedEntity getAllowedCode() {
        return allowedCode;
    }

    public void setAllowedCode(TranAllowedEntity allowedCode) {
        this.allowedCode = allowedCode;
    }

    public String getRountingGroup() {
        return rountingGroup;
    }

    public void setRountingGroup(String rountingGroup) {
        this.rountingGroup = rountingGroup;
    }

    @Override
    public String toString() {
        return "ApcEntity{" +
                "key='" + key + '\'' +
                ", idf=" + idf +
                ", sharingGroup='" + sharingGroup + '\'' +
                ", allowedCode=" + allowedCode +
                ", rountingGroup='" + rountingGroup + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApcEntity apcEntity = (ApcEntity) o;

        if (key != null ? !key.equals(apcEntity.key) : apcEntity.key != null) return false;
        if (idf != null ? !idf.equals(apcEntity.idf) : apcEntity.idf != null) return false;
        if (sharingGroup != null ? !sharingGroup.equals(apcEntity.sharingGroup) : apcEntity.sharingGroup != null)
            return false;
        if (allowedCode != null ? !allowedCode.equals(apcEntity.allowedCode) : apcEntity.allowedCode != null)
            return false;
        return rountingGroup != null ? rountingGroup.equals(apcEntity.rountingGroup) : apcEntity.rountingGroup == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (idf != null ? idf.hashCode() : 0);
        result = 31 * result + (sharingGroup != null ? sharingGroup.hashCode() : 0);
        result = 31 * result + (allowedCode != null ? allowedCode.hashCode() : 0);
        result = 31 * result + (rountingGroup != null ? rountingGroup.hashCode() : 0);
        return result;
    }
}
