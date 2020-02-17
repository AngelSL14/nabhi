package us.gonet.serverutils.model.jdb;

public class IDF {

    private String fiid;
    private String logicalNet;
    private String name;
    private String acquiringId;
    private String nameLong;
    private Country country;

    public String getFiid() {
        return fiid;
    }

    public void setFiid(String fiid) {
        this.fiid = fiid;
    }

    public String getLogicalNet() {
        return logicalNet;
    }

    public void setLogicalNet(String logicalNet) {
        this.logicalNet = logicalNet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcquiringId() {
        return acquiringId;
    }

    public void setAcquiringId(String acquiringId) {
        this.acquiringId = acquiringId;
    }

    public String getNameLong() {
        return nameLong;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "IDF{" +
                "fiid='" + fiid + '\'' +
                ", logicalNet='" + logicalNet + '\'' +
                ", name='" + name + '\'' +
                ", acquiringId='" + acquiringId + '\'' +
                ", nameLong='" + nameLong + '\'' +
                ", country=" + country +
                '}';
    }
}
