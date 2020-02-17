package us.gonet.serverutils.model.jdb.lite;

public class ButtonLite {

    @Override
    public String toString() {
        return "ButtonLite{" +
                "id=" + id +
                ", atm='" + atm + '\'' +
                ", screen='" + screen + '\'' +
                ", activeFdk='" + activeFdk + '\'' +
                '}';
    }

    private Integer id;

    private AtmDashboard atm;

    private String activeFdk;

    private String screen;

    public String getActiveFdk() {
        return activeFdk;
    }

    public void setActiveFdk(String activeFdk) {
        this.activeFdk = activeFdk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AtmDashboard getAtm() {
        return atm;
    }

    public void setAtm(AtmDashboard atm) {
        this.atm = atm;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }


}
