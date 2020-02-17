package com.dksits.jke.model;

import com.google.gson.GsonBuilder;

public class TPK extends KEY {
    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
