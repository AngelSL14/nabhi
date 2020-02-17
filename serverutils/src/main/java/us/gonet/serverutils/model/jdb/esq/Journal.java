package us.gonet.serverutils.model.jdb.esq;

public class Journal {

    private String message;
    private String atm;
    private String writeDate;


    public String getMessage () {
        return message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    public String getAtm () {
        return atm;
    }

    public void setAtm ( String atm ) {
        this.atm = atm;
    }

    public String getWriteDate () {
        return writeDate;
    }

    public void setWriteDate ( String writeDate ) {
        this.writeDate = writeDate;
    }

    @Override
    public String toString () {
        return "Journal{" +
                "message='" + message + '\'' +
                ", atm='" + atm + '\'' +
                ", writeDate='" + writeDate + '\'' +
                '}';
    }
}
