package us.gonet.jxiserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("props")
public class Properties{

    @Value("${protocol}")
    private String protocol;

    @Value( "${delt.key}" )
    private String key;
    @Value( "${local.user.tkn}" )
    private String localUserTkn;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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
