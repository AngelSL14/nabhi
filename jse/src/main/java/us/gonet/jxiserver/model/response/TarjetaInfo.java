package us.gonet.jxiserver.model.response;

import us.gonet.jxiserver.model.StylesBank;

public class TarjetaInfo{

    private String bank;
    private String message;
    private StylesBank styles;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StylesBank getStyles() {
        return styles;
    }

    public void setStyles(StylesBank styles) {
        this.styles = styles;
    }

    public  TarjetaInfo(String bank, String message,StylesBank styles){
        this.bank=bank;
        this.message=message;
        this.styles=styles;
    }

    public  TarjetaInfo(String bank, String message){
        this.bank=bank;
        this.message=message;
    }

    @Override
    public String toString() {
        return "TarjetaInfo{" +
                "bank='" + bank + '\'' +
                ", message='" + message + '\'' +
                ", styles=" + styles +
                '}';
    }
}
