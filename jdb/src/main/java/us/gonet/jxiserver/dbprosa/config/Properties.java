package us.gonet.jxiserver.dbprosa.config;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Properties that = (Properties) o;

        if (protocol != null ? !protocol.equals(that.protocol) : that.protocol != null) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        return localUserTkn != null ? localUserTkn.equals(that.localUserTkn) : that.localUserTkn == null;
    }

    @Override
    public int hashCode() {
        int result = protocol != null ? protocol.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (localUserTkn != null ? localUserTkn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "protocol='" + protocol + '\'' +
                ", key='" + key + '\'' +
                ", localUserTkn='" + localUserTkn + '\'' +
                '}';
    }
}
