package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

 @Component
public class WdlProperties extends Properties {

    @Value("${wdl.port}")
    private String port;

     public String getUser() {
         return user;
     }

     public void setUser(String user) {
         this.user = user;
     }

     @Value("${wdl.path}")
    private String path;
    @Value("${wdl.tkn.pwd}")
    private String pwd;

     public String getPort() {
         return port;
     }
     @Value("${wdl.tkn.user}")
     private String user;

     public void setPath(String path) {
         this.path = path;
     }

     public String getPwd() {
         return pwd;
     }
     @Value("${wdl.host}")
     private String host;
     public void setPort(String port) {
         this.port = port;
     }


     public String getHost() {
         return host;
     }

     public void setHost(String host) {
         this.host = host;
     }

     public String getPath() {
         return path;
     }


     public void setPwd(String pwd) {
         this.pwd = pwd;
     }
 }

