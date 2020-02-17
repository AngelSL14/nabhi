package us.gonet.serverutils.model.jke;

import com.google.gson.GsonBuilder;

public class TPK extends TMK{

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
