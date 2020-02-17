package com.dksits.jke.model;

import com.google.gson.GsonBuilder;

public class PinBlock extends ATM{


    private NodeSingleModel nodeSingle;

    public NodeSingleModel getNodeSingle () {
        return nodeSingle;
    }

    public void setNodeSingle ( NodeSingleModel nodeSingle ) {
        this.nodeSingle = nodeSingle;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
