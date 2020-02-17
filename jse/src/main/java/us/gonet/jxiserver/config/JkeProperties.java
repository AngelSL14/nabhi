package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JkeProperties extends Properties {
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Value("${jke.path}")
    private String path;

    @Value("${jke.host}")
    private String host;

    @Value("${jke.port}")
    private String port;

    @Value("${jke.tkn.user}")
    private String user;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }
    @Value("${jke.tkn.pwd}")
    private String pwd;

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }


}
