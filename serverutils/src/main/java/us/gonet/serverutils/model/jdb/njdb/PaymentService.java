package us.gonet.serverutils.model.jdb.njdb;

import com.google.gson.GsonBuilder;

public class PaymentService implements IJdb{

    private int id;
    private String name;
    private String imageUrl;
    private boolean active;

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

    public String getImageUrl () {
        return imageUrl;
    }

    public void setImageUrl ( String imageUrl ) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive () {
        return active;
    }

    public void setActive ( boolean active ) {
        this.active = active;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
