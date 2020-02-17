package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;

@Entity @Table(name = "TBL_BUTTONS")
public class ButtonsEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID_BUTTON")
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_ATM_TERMINAL_ID", foreignKey = @ForeignKey(name = "FK_ATM_BUTTONS"))
    private AtmEntity atm;

    @Column(length = 50)
    private String screen;

    @Column(length = 8)
    private String activeFdk;

    public ButtonsEntity() {
    }

    public ButtonsEntity(AtmEntity atm, String screen, String activeFdk) {
        this.atm = atm;
        this.screen = screen;
        this.activeFdk = activeFdk;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AtmEntity getAtm() {
        return atm;
    }

    public void setAtm(AtmEntity atm) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ButtonsEntity that = (ButtonsEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (atm != null ? !atm.equals(that.atm) : that.atm != null) return false;
        if (screen != null ? !screen.equals(that.screen) : that.screen != null) return false;
        return activeFdk != null ? activeFdk.equals(that.activeFdk) : that.activeFdk == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (atm != null ? atm.hashCode() : 0);
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        result = 31 * result + (activeFdk != null ? activeFdk.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ButtonsEntity{" +
                "id=" + id +
                ", atm=" + atm +
                ", screen='" + screen + '\'' +
                ", activeFdk='" + activeFdk + '\'' +
                '}';
    }
}
