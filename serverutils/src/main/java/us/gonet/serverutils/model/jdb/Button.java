package us.gonet.serverutils.model.jdb;

public class Button {

    private int id;
    private Atm atm;
    private String screen;
    private String activeFdk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getActiveFdk() {
        return activeFdk;
    }

    public void setActiveFdk(String activeFdk) {
        this.activeFdk = activeFdk;
    }

    @Override
    public String toString() {
        return "Button{" +
                "id=" + id +
                ", atm=" + atm +
                ", screen='" + screen + '\'' +
                ", activeFdk='" + activeFdk + '\'' +
                '}';
    }
}
