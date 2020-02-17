package us.gonet.jxiserver.security.model;

import com.google.gson.GsonBuilder;

public class User {

    private String id;
    private String password;
    private byte rol;

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public byte getRol () {
        return rol;
    }

    public void setRol ( byte rol ) {
        this.rol = rol;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
