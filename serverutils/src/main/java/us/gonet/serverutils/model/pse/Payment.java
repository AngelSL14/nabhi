package us.gonet.serverutils.model.pse;

import com.google.gson.GsonBuilder;

public class Payment {

    private int id;
    private String name;
    private String reference;

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getReference () {
        return reference;
    }

    public void setReference ( String reference ) {
        this.reference = reference;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
