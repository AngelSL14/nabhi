package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InqProperties extends Properties {

    @Value("${inq.port}")
    private String port;
    @Value("${inq.host}")
    private String host;
    @Value("${inq.path}")
    private String path;
    @Value("${inq.tkn.pwd}")
    private String pwd;
    @Value("${inq.tkn.user}")
    private String user;



    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public String getPwd() {
        return pwd;
    }
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }
    public String getPath() {
        return path;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
