package us.gonet.serverutils.model.jdb;

public class State {

    private String stateCode;
    private String stateName;
    private String stateShortName;
    private String zone;

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
    public String toString() {
        return "State{" +
                "stateCode='" + stateCode + '\'' +
                ", stateName='" + stateName + '\'' +
                ", stateShortName='" + stateShortName + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}
