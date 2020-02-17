package us.gonet.serverutils.model.jdb;

public class County {

    private int countyCodeId;
    private String countyCode;
    private String countyName;
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CountyLite{" +
                "countyCodeId=" + countyCodeId +
                ", countyCode='" + countyCode + '\'' +
                ", countyName='" + countyName + '\'' +
                ", state=" + state +
                '}';
    }
}
