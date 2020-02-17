package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;

@Entity @Table(name = "TBL_DASHBOARD_USERS")
public class DashboardUsersEntity {

    @Id @Column(name = "PK_ID_USER" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUser;

    @Column(length = 60)
    private String password;

    @Column(length = 10)
    private String phoneNumber;

    @Column(length = 30, unique = true)
    private String email;

    @Column(length = 30)
    private String fullname;

    @Column(length = 1)
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getIdUser() {

        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }



    @Override
    public String toString() {
        return "DashboardUsersEntity{" +
                "idUser='" + idUser + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
