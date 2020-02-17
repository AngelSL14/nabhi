package us.gonet.jxiserver.model.request;

import java.util.List;


public class AtmInfo {
    private String ip;
    private String termId;
    private String track;

    private String transactionCode;

    private List<String> buffer;



    public AtmInfo() {
    }

    public AtmInfo(String ip, String track, String transactionCode, List<String> buffer, String termId) {
        this.ip = ip;
        this.track = track;
        this.transactionCode = transactionCode;
        this.buffer = buffer;
        this.termId = termId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public List<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(List<String> buffer) {
        this.buffer = buffer;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AtmInfo atmInfo = (AtmInfo) o;

        if (ip != null ? !ip.equals(atmInfo.ip) : atmInfo.ip != null) return false;
        if (track != null ? !track.equals(atmInfo.track) : atmInfo.track != null) return false;
        if (transactionCode != null ? !transactionCode.equals(atmInfo.transactionCode) : atmInfo.transactionCode != null)
            return false;
        if (buffer != null ? !buffer.equals(atmInfo.buffer) : atmInfo.buffer != null) return false;
        return termId != null ? termId.equals(atmInfo.termId) : atmInfo.termId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (track != null ? track.hashCode() : 0);
        result = 31 * result + (transactionCode != null ? transactionCode.hashCode() : 0);
        result = 31 * result + (buffer != null ? buffer.hashCode() : 0);
        result = 31 * result + (termId != null ? termId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AtmInfo{" +
                "ip='" + ip + '\'' +
                ", track='" + track + '\'' +
                ", transactionCode='" + transactionCode + '\'' +
                ", buffer=" + buffer +
                ", termId='" + termId + '\'' +
                '}';
    }
}
