package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "TBL_TOKEN_USERS")

public class TokenUsersEntity {

    @Id
    @Column(name = "PK_ID_TOKEN")
    private String id;

    @Column(length = 60)
    private String clave;

    private int rol;

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TokenUsersEntity entity = (TokenUsersEntity) o;

        if (rol != entity.rol) return false;
        if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
        return clave != null ? clave.equals(entity.clave) : entity.clave == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (clave != null ? clave.hashCode() : 0);
        result = 31 * result + rol;
        return result;
    }

    @Override
    public String
    toString() {
        return "TokenUsersEntity{" +
                "id='" + id + '\'' +
                ", clave='" + clave + '\'' +
                ", rol=" + rol +
                '}';
    }
}
