package us.gonet.serverutils.model.jdb;

public class DeviceNotif {

    private int idDeviceNotif;
    private String fkAtmTerminalId;
    private String message;

    public DeviceNotif () {
    }

    public DeviceNotif ( int idDeviceNotif, String fkAtmTerminalId, String message ) {
        this.idDeviceNotif = idDeviceNotif;
        this.fkAtmTerminalId = fkAtmTerminalId;
        this.message = message;
    }

    public int getIdDeviceNotif () {
        return idDeviceNotif;
    }

    public void setIdDeviceNotif ( int idDeviceNotif ) {
        this.idDeviceNotif = idDeviceNotif;
    }

    public String getFkAtmTerminalId () {
        return fkAtmTerminalId;
    }

    public void setFkAtmTerminalId ( String fkAtmTerminalId ) {
        this.fkAtmTerminalId = fkAtmTerminalId;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    @Override
    public String toString () {
        return "DeviceNotif{" +
                "idDeviceNotif=" + idDeviceNotif +
                ", fkAtmTerminalId='" + fkAtmTerminalId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
