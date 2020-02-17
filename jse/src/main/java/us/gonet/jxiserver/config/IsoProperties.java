package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IsoProperties extends Properties {
    @Value("${iso.path}")
    private String path;
    @Value("${iso.port}")
    private String port;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    @Value("${iso.host}")
    private String host;
    public void setHost(String host) {
        this.host = host;
    }

    public String getPwd() {
        return pwd;
    }

    @Value("${iso.tkn.pwd}")
    private String pwd;

    @Value("${iso.tkn.user}")
    private String user;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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



    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
