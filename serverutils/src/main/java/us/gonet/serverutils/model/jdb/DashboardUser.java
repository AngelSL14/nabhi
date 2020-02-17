package us.gonet.serverutils.model.jdb;

public class DashboardUser {

    private String idUser;
    private String password;
    private String phoneNumber;
    private String email;
    private String fullname;
    private String rol;

    public String getIdUser () {
        return idUser;
    }

    public void setIdUser ( String idUser ) {
        this.idUser = idUser;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public void setPhoneNumber ( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getFullname () {
        return fullname;
    }

    public void setFullname ( String fullname ) {
        this.fullname = fullname;
    }

    public String getRol () {
        return rol;
    }

    public void setRol ( String rol ) {
        this.rol = rol;
    }

    @Override
    public String toString () {
        return "DashboardUser{" +
                "idUser='" + idUser + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
