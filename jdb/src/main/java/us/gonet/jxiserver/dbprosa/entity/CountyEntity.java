package us.gonet.jxiserver.dbprosa.entity;

import com.google.gson.GsonBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "TBL_COUNTY" )

public class CountyEntity implements Serializable{

    @Id
    @Column( name = "PK_COUNTY_CODE_ID" )
    private int countyCodeId;

    private static final long serialVersionUID = 2405172261950251807L;



    public static long getSerialVersionUID() {

        return serialVersionUID;
    }
    @Column( name = "COUNTY_CODE", length = 3 )
    private String countyCode;
    @Column( name = "COUNTY_NAME", length = 45 )
    private String countyName;
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

    @ManyToOne( optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn( name = "FK_STATE_CODE", foreignKey = @ForeignKey( name = "FK_STATE_CODE" ) )
    @Fetch( FetchMode.JOIN )
    private StateEntity fkStateEntity;

    public CountyEntity() {
    }

    public CountyEntity(int countyCodeId, String countyCode, String countyName, StateEntity fkStateEntity) {
        this.countyCodeId = countyCodeId;
        this.countyCode = countyCode;
        this.countyName = countyName;
        this.fkStateEntity = fkStateEntity;
    }


    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public StateEntity getFkStateEntity() {
        return fkStateEntity;
    }

    public void setFkStateEntity(StateEntity fkStateEntity) {
        this.fkStateEntity = fkStateEntity;
    }



    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
