package us.gonet.serverutils.model.jdb;

public class BinModel {

    private String bin;
    private String id;
    private IDF idf;

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IDF getIdf() {
        return idf;
    }

    public void setIdf(IDF idf) {
        this.idf = idf;
    }

    @Override
    public String toString() {
        return "BinModel{" +
                "bin='" + bin + '\'' +
                ", id='" + id + '\'' +
                ", idf=" + idf +
                '}';
    }
}
