package us.gonet.jxiserver.dbprosa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JdbProperties extends Properties{
    @Value("${jdb.host}")
    private String host;
    @Value("${jdb.port}")
    private String port;
    @Value("${jdb.path}")
    private String path;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JdbProperties that = (JdbProperties) o;

        return (host != null ? host.equals(that.host) : that.host == null) && (port != null ? port.equals(that.port) : that.port == null) && (path != null ? path.equals(that.path) : that.path == null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JdbProperties{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
