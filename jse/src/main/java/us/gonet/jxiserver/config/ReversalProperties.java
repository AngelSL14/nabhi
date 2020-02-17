package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReversalProperties extends Properties{
    @Value("${reversal.tkn.user}")
    private String user;

    @Value("${reversal.port}")
    private String port;

    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }

    public void setHost(String host) {
        this.host = host;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHost() {
        return host;
    }

    @Value("${reversal.path}")
    private String path;

    @Value("${reversal.tkn.pwd}")
    private String pwd;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    @Value("${reversal.host}")
    private String host;

}
