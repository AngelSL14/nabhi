package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StmtProperties extends Properties{

    @Value("${stmt.host}")
    private String host;
    @Value("${stmt.tkn.pwd}")
    private String pwd;
    @Value("${stmt.tkn.user}")
    private String user;
    @Value("${stmt.path}")
    private String path;


    @Value("${stmt.port}")
    private String port;


    public void setHost(String host) {
        this.host = host;
    }
    public String getUser() {
        return user;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }



    public void setUser(String user) {
        this.user = user;
    }



    public void setPath(String path) {
        this.path = path;
    }

    public String getPort() {
        return port;
    }
    public String getPath() {
        return path;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getHost() {
        return host;
    }

}
