package us.gonet.jxiserver.model.response;

public class ScreenCapabilities {

    private String screen;
    private String activeFDKs;

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getActiveFDKs() {
        return activeFDKs;
    }

    public void setActiveFDKs(String activeFDKs) {
        this.activeFDKs = activeFDKs;
    }



    public String toString(){
        return "Screeen : "+screen + "   ActiveFDKs : "+activeFDKs;
    }
}
