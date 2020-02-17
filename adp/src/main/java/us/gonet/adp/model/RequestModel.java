package us.gonet.adp.model;

public class RequestModel
{
    private String ip;
    private String cashWithAmount;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCashWithAmount() {
        return cashWithAmount;
    }

    public void setCashWithAmount(String cashWithAmount) {
        this.cashWithAmount = cashWithAmount;
    }

    public RequestModel() {
    }

    public RequestModel(String ip, String cashWithAmount) {
        this.ip = ip;
        this.cashWithAmount = cashWithAmount;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "ip='" + ip + '\'' +
                ", cashWithAmount='" + cashWithAmount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestModel that = (RequestModel) o;

        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        return cashWithAmount != null ? cashWithAmount.equals(that.cashWithAmount) : that.cashWithAmount == null;
    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (cashWithAmount != null ? cashWithAmount.hashCode() : 0);
        return result;
    }
}
