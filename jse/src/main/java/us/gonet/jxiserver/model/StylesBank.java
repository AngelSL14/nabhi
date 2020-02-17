package us.gonet.jxiserver.model;

public class StylesBank {

    private String id;
    private String buttons;
    private String dashboard;
    private String sections;

    public StylesBank(String buttons, String dashboard, String sections){
        this.buttons=buttons;
        this.dashboard=dashboard;
        this.sections=sections;
    }

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
        return "StylesBank{" +
                "id='" + id + '\'' +
                ", buttons='" + buttons + '\'' +
                ", dash='" + dashboard + '\'' +
                ", sections='" + sections + '\'' +
                '}';
    }
}
