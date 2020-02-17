package us.gonet.serverutils.model.jdb;

public class BankStyle {

    private String id;
    private String buttons;
    private String dashboard;
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
    public String toString() {
        return "BankStyle{" +
                "id='" + id + '\'' +
                ", buttons='" + buttons + '\'' +
                ", dashboard='" + dashboard + '\'' +
                ", sections='" + sections + '\'' +
                '}';
    }
}
