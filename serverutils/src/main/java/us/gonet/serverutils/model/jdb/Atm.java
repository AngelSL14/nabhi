package us.gonet.serverutils.model.jdb;

public class Atm {

    private String id;
    private String ip;
    private String lastTrx;
    private boolean activeTrx;
    private String sucursal;
    private String receipt;

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

    public String getLastTrx() {
        return lastTrx;
    }

    public void setLastTrx(String lastTrx) {
        this.lastTrx = lastTrx;
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

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", lastTrx='" + lastTrx + '\'' +
                ", activeTrx=" + activeTrx +
                ", sucursal='" + sucursal + '\'' +
                ", receipt='" + receipt + '\'' +
                '}';
    }
}
