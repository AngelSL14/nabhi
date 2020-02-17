package us.gonet.jxiserver.dbprosa.entity;

import com.google.gson.GsonBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "TBL_COUNTRY" )
public class CountryEntity implements Serializable{

    @Id
    @Column( name = "PK_COUNTRY_CODE", length = 3 )
    private String countryCode;
    @Column( name = "ALPHA2", length = 2 )
    private String alpha2;
    @Column( name = "ALPHA3", length = 3 )
    private String alpha3;
    @Column( name = "NAME", length = 20 )
    private String name;
    @Column( name = "SYMBOLS", length = 1 )
    private String symbols;

    public CountryEntity() {
    }

    public CountryEntity(String countryCode, String alpha2, String alpha3, String name, String symbols) {
        this.countryCode = countryCode;
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.name = name;
        this.symbols = symbols;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CountryEntity entity = (CountryEntity) o;

        if (countryCode != null ? !countryCode.equals(entity.countryCode) : entity.countryCode != null) return false;
        if (alpha2 != null ? !alpha2.equals(entity.alpha2) : entity.alpha2 != null) return false;
        if (alpha3 != null ? !alpha3.equals(entity.alpha3) : entity.alpha3 != null) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        return symbols != null ? symbols.equals(entity.symbols) : entity.symbols == null;
    }
    private static final long serialVersionUID = 2405172041100251807L;



    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (alpha2 != null ? alpha2.hashCode() : 0);
        result = 31 * result + (alpha3 != null ? alpha3.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (symbols != null ? symbols.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
