package us.gonet.security.rest.model;


public class User {

    private String id;
    private String p;
    private byte q;

    public User(){}

    public User ( String id, String p, byte q ) {
        this.id = id;
        this.p = p;
        this.q = q;
    }

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getP () {
        return p;
    }

    public void setP ( String p ) {
        this.p = p;
    }

    public byte getQ () {
        return q;
    }

    public void setQ ( byte q ) {
        this.q = q;
    }

    @Override
    public String toString () {
        return id + ( char ) 28 + p + ( char ) 28 + q;
    }
}
