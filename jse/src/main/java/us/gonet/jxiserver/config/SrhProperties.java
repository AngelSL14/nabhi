package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SrhProperties extends Properties {
    @Value("${srh.path}")
    private String path;
    @Value("${srh.tkn.pwd}")
    private String pwd;
    @Value("${srh.port}")
    private String port;
    @Value("${srh.host}")
    private String host;
    @Value("${srh.tkn.user}")
    private String user;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
