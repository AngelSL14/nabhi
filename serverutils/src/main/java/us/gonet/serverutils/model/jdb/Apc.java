package us.gonet.serverutils.model.jdb;

public class Apc {

    private String key;
    private IDF idf;
    private String sharingGroup;
    private TranAllowed allowedCode;
    private String rountingGroup;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public IDF getIdf() {
        return idf;
    }

    public void setIdf(IDF idf) {
        this.idf = idf;
    }

    public String getSharingGroup() {
        return sharingGroup;
    }

    public void setSharingGroup(String sharingGroup) {
        this.sharingGroup = sharingGroup;
    }

    public TranAllowed getAllowedCode() {
        return allowedCode;
    }

    public void setAllowedCode(TranAllowed allowedCode) {
        this.allowedCode = allowedCode;
    }

    public String getRountingGroup() {
        return rountingGroup;
    }

    public void setRountingGroup(String rountingGroup) {
        this.rountingGroup = rountingGroup;
    }

    @Override
    public String toString() {
        return "Apc{" +
                "key='" + key + '\'' +
                ", idf=" + idf +
                ", sharingGroup='" + sharingGroup + '\'' +
                ", allowedCode=" + allowedCode +
                ", rountingGroup='" + rountingGroup + '\'' +
                '}';
    }
}
