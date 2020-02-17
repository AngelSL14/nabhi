package us.gonet.jxiserver.model.request;

import java.util.Map;

public class AtmNotificationModel {
    private String ip;
    private String termId;
    private String device;
    private String status;
    private Map<String, Object> extra;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AtmNotificationModel that = (AtmNotificationModel) o;

        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (termId != null ? !termId.equals(that.termId) : that.termId != null) return false;
        if (device != null ? !device.equals(that.device) : that.device != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return extra != null ? extra.equals(that.extra) : that.extra == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (termId != null ? termId.hashCode() : 0);
        result = 31 * result + (device != null ? device.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        return result;
    }

    @Override
    public String
    toString() {
        return "AtmNotificationModel{" +
                "ip='" + ip + '\'' +
                ", termId='" + termId + '\'' +
                ", device='" + device + '\'' +
                ", status='" + status + '\'' +
                ", extra=" + extra +
                '}';
    }
}
