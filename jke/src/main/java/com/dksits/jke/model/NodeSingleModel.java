package com.dksits.jke.model;

import com.google.gson.GsonBuilder;

public class NodeSingleModel {

    private int idNode;
    private String nodeName;

    public int getIdNode () {
        return idNode;
    }

    public void setIdNode ( int idNode ) {
        this.idNode = idNode;
    }

    public String getNodeName () {
        return nodeName;
    }

    public void setNodeName ( String nodeName ) {
        this.nodeName = nodeName;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
