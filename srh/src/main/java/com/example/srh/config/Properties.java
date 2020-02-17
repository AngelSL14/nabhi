
package com.example.srh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("props")

public class Properties {

    @Value( "${protocol}" )
    private String protocol;
    @Value( "${jdb.host}" )
    private String jdbHost;
    @Value( "${jdb.port}" )
    private String jdbPort;
    @Value( "${jdb.path}" )
    private String jdbPath;
    @Value("${jdb.tkn.user}")
    private String tknUser;
    @Value("${jdb.tkn.pwd}")
    private String tknPwd;

    @Value( "${delt.key}" )
    private String key;
    @Value( "${local.user.tkn}" )
    private String localUserTkn;

    @Override
    public String toString() {
        return "Properties{" +
                "protocol='" + protocol + '\'' +
                ", jdbHost='" + jdbHost + '\'' +
                ", jdbPort='" + jdbPort + '\'' +
                ", jdbPath='" + jdbPath + '\'' +
                ", tknUser='" + tknUser + '\'' +
                ", tknPwd='" + tknPwd + '\'' +
                ", key='" + key + '\'' +
                ", localUserTkn='" + localUserTkn + '\'' +
                '}';
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getJdbHost() {
        return jdbHost;
    }

    public void setJdbHost(String jdbHost) {
        this.jdbHost = jdbHost;
    }

    public String getJdbPort() {
        return jdbPort;
    }

    public void setJdbPort(String jdbPort) {
        this.jdbPort = jdbPort;
    }

    public String getJdbPath() {
        return jdbPath;
    }

    public void setJdbPath(String jdbPath) {
        this.jdbPath = jdbPath;
    }

    public String getTknUser() {
        return tknUser;
    }

    public void setTknUser(String tknUser) {
        this.tknUser = tknUser;
    }

    public String getTknPwd() {
        return tknPwd;
    }

    public void setTknPwd(String tknPwd) {
        this.tknPwd = tknPwd;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocalUserTkn() {
        return localUserTkn;
    }

    public void setLocalUserTkn(String localUserTkn) {
        this.localUserTkn = localUserTkn;
    }
}
