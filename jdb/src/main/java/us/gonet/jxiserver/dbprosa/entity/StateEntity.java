package us.gonet.jxiserver.dbprosa.entity;

import com.google.gson.GsonBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "TBL_STATE" )

public class StateEntity implements Serializable{

    @Id
    @Column( name = "PK_STATE_CODE", length = 2 )
    private String stateCode;
    @Column( name = "STATE_NAME", length = 20 )
    private String stateName;
    @Column( name = "STATE_SHORT_NAME", length = 3 )
    private String stateShortName;
    private static final long serialVersionUID = 2405172041950372807L;



    public static long getSerialVersionUID() {

        return serialVersionUID;
    }
    private String zone;

    public StateEntity() {
    }

    public StateEntity(String stateCode, String stateName, String stateShortName, String zone) {
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.stateShortName = stateShortName;
        this.zone = zone;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateShortName() {
        return stateShortName;
    }

    public void setStateShortName(String stateShortName) {
        this.stateShortName = stateShortName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StateEntity that = (StateEntity) o;

        if (stateCode != null ? !stateCode.equals(that.stateCode) : that.stateCode != null) return false;
        if (stateName != null ? !stateName.equals(that.stateName) : that.stateName != null) return false;
        if (stateShortName != null ? !stateShortName.equals(that.stateShortName) : that.stateShortName != null)
            return false;
        return zone != null ? zone.equals(that.zone) : that.zone == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (stateCode != null ? stateCode.hashCode() : 0);
        result = 31 * result + (stateName != null ? stateName.hashCode() : 0);
        result = 31 * result + (stateShortName != null ? stateShortName.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
