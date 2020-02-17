package us.gonet.serverutils.model.jdb;

public class DeviceStatus {

    private int id;
    private Atm atm;
    private String status;
    private Device device;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "DeviceStatus{" +
                "id=" + id +
                ", atm=" + atm +
                ", status='" + status + '\'' +
                ", device=" + device +
                '}';
    }
}
