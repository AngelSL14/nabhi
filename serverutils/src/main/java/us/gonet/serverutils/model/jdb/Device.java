package us.gonet.serverutils.model.jdb;

public class Device {

    private int idDevice;
    private String type;

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Device{" +
                "idDevice=" + idDevice +
                ", type='" + type + '\'' +
                '}';
    }
}
