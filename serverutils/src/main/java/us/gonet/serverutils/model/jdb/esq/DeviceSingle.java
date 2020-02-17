package us.gonet.serverutils.model.jdb.esq;

import java.util.List;

public class DeviceSingle {

    private int idDevice;
    private String type;
    private String status;
    private List content;

    public int getIdDevice () {
        return idDevice;
    }

    public void setIdDevice ( int idDevice ) {
        this.idDevice = idDevice;
    }

    public String getType () {
        return type;
    }

    public void setType ( String type ) {
        this.type = type;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus ( String status ) {
        this.status = status;
    }

    public List getContent () {
        return content;
    }

    public void setContent ( List content ) {
        this.content = content;
    }

    @Override
    public String toString () {
        return "DeviceSingle{" +
                "idDevice=" + idDevice +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", content=" + content +
                '}';
    }
}
