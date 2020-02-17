package us.gonet.serverutils.model.jdb;

public class Screen {

    private Atm atm;
    private String screenType;
    private String extPublicity;
    private String namePublicity;
    private BankStyle bankStyle;

    public Atm getAtm () {
        return atm;
    }

    public void setAtm ( Atm atm ) {
        this.atm = atm;
    }

    public String getScreenType () {
        return screenType;
    }

    public void setScreenType ( String screenType ) {
        this.screenType = screenType;
    }

    public String getExtPublicity () {
        return extPublicity;
    }

    public void setExtPublicity ( String extPublicity ) {
        this.extPublicity = extPublicity;
    }

    public String getNamePublicity () {
        return namePublicity;
    }

    public void setNamePublicity ( String namePublicity ) {
        this.namePublicity = namePublicity;
    }

    public BankStyle getBankStyle () {
        return bankStyle;
    }

    public void setBankStyle ( BankStyle bankStyle ) {
        this.bankStyle = bankStyle;
    }

    @Override
    public String toString () {
        return "Screen{" +
                "atm=" + atm +
                ", screenType='" + screenType + '\'' +
                ", extPublicity='" + extPublicity + '\'' +
                ", namePublicity='" + namePublicity + '\'' +
                ", bankStyle=" + bankStyle +
                '}';
    }
}
