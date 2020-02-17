package com.dksits.jke.model;

import com.google.gson.GsonBuilder;

public class KEY {
    private String termType;
    private String atmRemote;
    private String atmLocal;

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getAtmRemote() {
        return atmRemote;
    }

    public void setAtmRemote(String atmRemote) {
        this.atmRemote = atmRemote;
    }

    public String getAtmLocal() {
        return atmLocal;
    }

    public void setAtmLocal(String atmLocal) {
        this.atmLocal = atmLocal;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
