package us.gonet.jxiserver.dbprosa.entity;


import javax.persistence.*;

@Entity @Table(name = "TBL_BANK_STYLE")
public class BankStyleEntity {

    @Id @Column( length = 45)
    private String id;

    @Lob
    private String buttons;

    @Column(length = 200)
    private String dashboard;

    @Column(length = 200)
    private String sections;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButtons() {
        return buttons;
    }

    public void setButtons(String buttons) {
        this.buttons = buttons;
    }

    public String getDashboard() {
        return dashboard;
    }

    public void setDashboard(String dashboard) {
        this.dashboard = dashboard;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankStyleEntity that = (BankStyleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (buttons != null ? !buttons.equals(that.buttons) : that.buttons != null) return false;
        if (dashboard != null ? !dashboard.equals(that.dashboard) : that.dashboard != null) return false;
        return sections != null ? sections.equals(that.sections) : that.sections == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (buttons != null ? buttons.hashCode() : 0);
        result = 31 * result + (dashboard != null ? dashboard.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BankStyleEntity{" +
                "id='" + id + '\'' +
                ", buttons='" + buttons + '\'' +
                ", dashboard='" + dashboard + '\'' +
                ", sections='" + sections + '\'' +
                '}';
    }
}
