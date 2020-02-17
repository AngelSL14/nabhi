package us.gonet.adp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("props")
public class Properties {

    @Value("${jdb.protocol}")
    private String protocol;

    @Value("${jdb.host}")
    private String host;

    @Value("${jdb.port}")
    private String port;

    @Value("${jdb.path}")
    private String path;

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
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", path='" + path + '\'' +
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
