package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JdbProperties extends Properties{
    @Value("${jdb.tkn.user}")
    private String user;

    @Value("${jdb.port}")
    private String port;
    @Value("${jdb.path}")
    private String path;
    @Value("${jdb.host}")
    private String host;

    public void setPath(String path) {
        this.path = path;
    }
    public String getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
    @Value("${jdb.tkn.pwd}")
    private String pwd;




    public void setPort(String port) {
        this.port = port;
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
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
