package us.gonet.serverutils.model.jdb.lite;

public class AtmDashboard {
    private String id;
    private String ip;
    private boolean activeTrx;
    private String sucursal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public boolean isActiveTrx() {
        return activeTrx;
    }

    public void setActiveTrx(boolean activeTrx) {
        this.activeTrx = activeTrx;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }


    @Override
    public String toString() {
        return "Atm{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", activeTrx=" + activeTrx +
                ", sucursal='" + sucursal + '\'' +
                '}';
    }
}
