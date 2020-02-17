package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NchProperties extends Properties{
    public void setUser(String user) {
        this.user = user;
    }

    public String getPort() {
        return port;
    }
    @Value("${nch.tkn.pwd}")
    private String pwd;

    @Value("${nch.tkn.user}")
    private String user;
    public void setHost(String host) {
        this.host = host;
    }

    @Value("${nch.port}")
    private String port;

    @Value("${nch.path}")
    private String path;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHost() {
        return host;
    }


    public String getUser() {
        return user;
    }



    public void setPort(String port) {
        this.port = port;
    }
    @Value("${nch.host}")
    private String host;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
