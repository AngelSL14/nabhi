package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "TBL_ATM_TRAN_ALLOW" )
public class TranAllowedEntity {

    @Id
    @Column(name = "PK_ALLOWED_CODE", length = 1)
    private String allowedCode;

    public String getAllowedCode() {
        return allowedCode;
    }

    public void setAllowedCode(String allowedCode) {
        this.allowedCode = allowedCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TranAllowedEntity that = (TranAllowedEntity) o;

        return allowedCode != null ? allowedCode.equals(that.allowedCode) : that.allowedCode == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (allowedCode != null ? allowedCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TranAllowedEntity{" +
                "allowedCode='" + allowedCode + '\'' +
                '}';
    }
}
