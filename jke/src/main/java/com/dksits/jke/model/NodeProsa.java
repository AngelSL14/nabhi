package com.dksits.jke.model;

import com.google.gson.GsonBuilder;

public class NodeProsa {

    private int idNode;
    private String ip;
    private String nodeName;
    private String port;
    private int tracerNumber;

    public int getIdNode () {
        return idNode;
    }

    public void setIdNode ( int idNode ) {
        this.idNode = idNode;
    }

    public String getIp () {
        return ip;
    }

    public void setIp ( String ip ) {
        this.ip = ip;
    }

    public String getNodeName () {
        return nodeName;
    }

    public void setNodeName ( String nodeName ) {
        this.nodeName = nodeName;
    }

    public String getPort () {
        return port;
    }

    public void setPort ( String port ) {
        this.port = port;
    }

    public int getTracerNumber () {
        return tracerNumber;
    }

    public void setTracerNumber ( int tracerNumber ) {
        this.tracerNumber = tracerNumber;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
