package us.gonet.jxiserver.dbprosa.entity.lite;

import javax.persistence.*;

@Entity @Table(name = "TBL_BUTTONS")
public class ButtonsEntityLite {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ButtonsEntityLite that = (ButtonsEntityLite) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (atm != null ? !atm.equals(that.atm) : that.atm != null) return false;
        if (screen != null ? !screen.equals(that.screen) : that.screen != null) return false;
        return activeFdk != null ? activeFdk.equals(that.activeFdk) : that.activeFdk == null;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID_BUTTON")
    private Integer id;

    @Column(name = "FK_ATM_TERMINAL_ID", length = 16)
    private String atm;
    public void setAtm(String atm) {
        this.atm = atm;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }
    @Column(length = 50)
    private String screen;

    @Column(length = 8)
    private String activeFdk;

    public ButtonsEntityLite() {
    }

    public ButtonsEntityLite(String atm, String screen, String activeFdk) {
        this.atm = atm;
        this.screen = screen;
        this.activeFdk = activeFdk;
    }
    public String getAtm() {
        return atm;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ButtonsEntityLite{" +
                "id=" + id +
                ", atm=" + atm +
                ", screen='" + screen + '\'' +
                ", activeFdk='" + activeFdk + '\'' +
                '}';
    }

    public void setActiveFdk(String activeFdk) {
        this.activeFdk = activeFdk;
    }

    public String getActiveFdk() {
        return activeFdk;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (atm != null ? atm.hashCode() : 0);
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        result = 31 * result + (activeFdk != null ? activeFdk.hashCode() : 0);
        return result;
    }


}
