package us.gonet.reversal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component( "props" )
public class Properties {

    @Value( "${protocol}" )
    private String protocol;
    @Value( "${jdb.host}" )
    private String jdbHost;
    @Value( "${jdb.port}" )
    private String jdbPort;
    @Value( "${jdb.path}" )
    private String jdbPath;
    @Value( "${jdb.tkn.user}" )
    private String jdbTknUser;
    @Value( "${jdb.tkn.pwd}" )
    private String jdbTknPwd;
    @Value( "${iso.host}" )
    private String isoHost;
    @Value( "${iso.port}" )
    private String isoPort;
    @Value( "${iso.path}" )
    private String isoPath;
    @Value( "${iso.tkn.user}" )
    private String isoTknUser;
    @Value( "${iso.tkn.pwd}" )
    private String isoTknPwd;
    @Value( "${jke.host}" )
    private String jkeHost;
    @Value( "${jke.port}" )
    private String jkePort;
    @Value( "${jke.path}" )
    private String jkePath;
    @Value( "${jke.tkn.user}" )
    private String jkeTknUser;
    @Value( "${jke.tkn.pwd}" )
    private String jkeTknPwd;
    @Value( "${rcpt.host}" )
    private String rcptHost;
    @Value( "${rcpt.port}" )
    private String rcptPort;
    @Value( "${rcpt.path}" )
    private String rcptPath;
    @Value( "${rcpt.tkn.user}" )
    private String rcptTknUser;
    @Value( "${rcpt.tkn.pwd}" )
    private String rcptTknPwd;
    @Value( "${delt.key}" )
    private String key;
    @Value( "${local.user.tkn}" )
    private String localUserTkn;

    public String getProtocol () {
        return protocol;
    }

    public void setProtocol ( String protocol ) {
        this.protocol = protocol;
    }

    public String getJdbHost () {
        return jdbHost;
    }

    public void setJdbHost ( String jdbHost ) {
        this.jdbHost = jdbHost;
    }

    public String getJdbPort () {
        return jdbPort;
    }

    public void setJdbPort ( String jdbPort ) {
        this.jdbPort = jdbPort;
    }

    public String getJdbPath () {
        return jdbPath;
    }

    public void setJdbPath ( String jdbPath ) {
        this.jdbPath = jdbPath;
    }

    public String getJdbTknUser () {
        return jdbTknUser;
    }

    public void setJdbTknUser ( String jdbTknUser ) {
        this.jdbTknUser = jdbTknUser;
    }

    public String getJdbTknPwd () {
        return jdbTknPwd;
    }

    public void setJdbTknPwd ( String jdbTknPwd ) {
        this.jdbTknPwd = jdbTknPwd;
    }

    public String getIsoHost () {
        return isoHost;
    }

    public void setIsoHost ( String isoHost ) {
        this.isoHost = isoHost;
    }

    public String getIsoPort () {
        return isoPort;
    }

    public void setIsoPort ( String isoPort ) {
        this.isoPort = isoPort;
    }

    public String getIsoPath () {
        return isoPath;
    }

    public void setIsoPath ( String isoPath ) {
        this.isoPath = isoPath;
    }

    public String getIsoTknUser () {
        return isoTknUser;
    }

    public void setIsoTknUser ( String isoTknUser ) {
        this.isoTknUser = isoTknUser;
    }

    public String getIsoTknPwd () {
        return isoTknPwd;
    }

    public void setIsoTknPwd ( String isoTknPwd ) {
        this.isoTknPwd = isoTknPwd;
    }

    public String getJkeHost () {
        return jkeHost;
    }

    public void setJkeHost ( String jkeHost ) {
        this.jkeHost = jkeHost;
    }

    public String getJkePort () {
        return jkePort;
    }

    public void setJkePort ( String jkePort ) {
        this.jkePort = jkePort;
    }

    public String getJkePath () {
        return jkePath;
    }

    public void setJkePath ( String jkePath ) {
        this.jkePath = jkePath;
    }

    public String getJkeTknUser () {
        return jkeTknUser;
    }

    public void setJkeTknUser ( String jkeTknUser ) {
        this.jkeTknUser = jkeTknUser;
    }

    public String getJkeTknPwd () {
        return jkeTknPwd;
    }

    public void setJkeTknPwd ( String jkeTknPwd ) {
        this.jkeTknPwd = jkeTknPwd;
    }

    public String getRcptHost () {
        return rcptHost;
    }

    public void setRcptHost ( String rcptHost ) {
        this.rcptHost = rcptHost;
    }

    public String getRcptPort () {
        return rcptPort;
    }

    public void setRcptPort ( String rcptPort ) {
        this.rcptPort = rcptPort;
    }

    public String getRcptPath () {
        return rcptPath;
    }

    public void setRcptPath ( String rcptPath ) {
        this.rcptPath = rcptPath;
    }

    public String getRcptTknUser () {
        return rcptTknUser;
    }

    public void setRcptTknUser ( String rcptTknUser ) {
        this.rcptTknUser = rcptTknUser;
    }

    public String getRcptTknPwd () {
        return rcptTknPwd;
    }

    public void setRcptTknPwd ( String rcptTknPwd ) {
        this.rcptTknPwd = rcptTknPwd;
    }

    public String getKey() {
        return key;
    }

    public String getLocalUserTkn() {
        return localUserTkn;
    }

}
