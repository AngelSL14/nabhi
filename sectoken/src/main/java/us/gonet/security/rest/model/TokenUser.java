package us.gonet.security.rest.model;

import com.google.gson.GsonBuilder;

public class TokenUser {

    private String clave;
    private String id;
    private int rol;

    public String getClave () {
        return clave;
    }

    public void setClave ( String clave ) {
        this.clave = clave;
    }

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public int getRol () {
        return rol;
    }

    public void setRol ( int rol ) {
        this.rol = rol;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
