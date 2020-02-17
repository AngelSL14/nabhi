package us.gonet.serverutils.model.receipt;


import com.google.gson.GsonBuilder;

public class Receipt {

    private String header;

    public Receipt () {
    }

    public Receipt ( String header, String body, String trailer ) {
        this.header = header;
        this.body = body;
        this.trailer = trailer;
    }

    private String body;
    private String trailer;

    public String getHeader () {
        return header;
    }

    public void setHeader ( String header ) {
        this.header = header;
    }

    public String getBody () {
        return body;
    }

    public void setBody ( String body ) {
        this.body = body;
    }

    public String getTrailer () {
        return trailer;
    }

    public void setTrailer ( String trailer ) {
        this.trailer = trailer;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
    public String getTicket(){
        return header + body + trailer;
    }
}
