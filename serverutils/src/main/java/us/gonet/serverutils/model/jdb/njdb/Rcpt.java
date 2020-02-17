package us.gonet.serverutils.model.jdb.njdb;

import com.google.gson.GsonBuilder;
import us.gonet.serverutils.model.receipt.Receipt;

public class Rcpt extends Receipt implements IJdb{

    private String key;

    public Rcpt (){}

    public Rcpt ( String header, String body, String trailer ) {
        super( header, body, trailer );
    }

    public String getKey() {
        return key;
    }

    public void setKey( String key ) {
        this.key = key;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
