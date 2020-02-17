package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;

@Entity @Table(name = "TBL_BIN")
public class BinEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String bin;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_IDF_FIID", foreignKey = @ForeignKey(name = "FK_BIN_IDF"))
    private IDFEntity idf;

    public BinEntity() {
    }

    public BinEntity(String bin, IDFEntity idf) {
        this.bin = bin;
        this.idf = idf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public IDFEntity getIdf() {
        return idf;
    }

    public void setIdf(IDFEntity idf) {
        this.idf = idf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinEntity entity = (BinEntity) o;

        if (id != entity.id) return false;
        if (bin != null ? !bin.equals(entity.bin) : entity.bin != null) return false;
        return idf != null ? idf.equals(entity.idf) : entity.idf == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bin != null ? bin.hashCode() : 0);
        result = 31 * result + (idf != null ? idf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BinEntity{" +
                "id=" + id +
                ", bin='" + bin + '\'' +
                ", idf=" + idf +
                '}';
    }
}
