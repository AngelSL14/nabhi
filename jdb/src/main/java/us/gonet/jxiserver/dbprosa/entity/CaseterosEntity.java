package us.gonet.jxiserver.dbprosa.entity;

import javax.persistence.*;

@Entity @Table(name = "TBL_CASETEROS")

public class CaseterosEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID_CASETERO")
    private int id;

    private int numberCasetero;

    private int incremento;
    private int decremento;
    private int actual;
    private int dispensados;
    private int depositados;
    private int reject;
    private int retract;
    private int denominacion;
    private int presentados;

    @Column(length = 15)
    private String status;

    @Column(length = 15)
    private String type;

    @Column(length = 3)
    private String currency;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_DEVICE_STATUS", foreignKey = @ForeignKey(name = "FK_DEVICE_ID"))
    private DeviceStatusEntity device;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberCasetero() {
        return numberCasetero;
    }

    public void setNumberCasetero(int numberCasetero) {
        this.numberCasetero = numberCasetero;
    }

    public int getIncremento() {
        return incremento;
    }

    public void setIncremento(int incremento) {
        this.incremento = incremento;
    }

    public int getDecremento() {
        return decremento;
    }

    public void setDecremento(int decremento) {
        this.decremento = decremento;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getDispensados() {
        return dispensados;
    }

    public void setDispensados(int dispensados) {
        this.dispensados = dispensados;
    }

    public int getDepositados() {
        return depositados;
    }

    public void setDepositados(int depositados) {
        this.depositados = depositados;
    }

    public int getReject() {
        return reject;
    }

    public void setReject(int reject) {
        this.reject = reject;
    }

    public int getRetract() {
        return retract;
    }

    public void setRetract(int retract) {
        this.retract = retract;
    }

    public int getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(int denominacion) {
        this.denominacion = denominacion;
    }

    public int getPresentados() {
        return presentados;
    }

    public void setPresentados(int presentados) {
        this.presentados = presentados;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public DeviceStatusEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceStatusEntity device) {
        this.device = device;
    }



    @Override
    public String toString() {
        return "CaseterosEntity{" +
                "id=" + id +
                ", numberCasetero=" + numberCasetero +
                ", incremento=" + incremento +
                ", decremento=" + decremento +
                ", actual=" + actual +
                ", dispensados=" + dispensados +
                ", depositados=" + depositados +
                ", reject=" + reject +
                ", retract=" + retract +
                ", denominacion=" + denominacion +
                ", presentados=" + presentados +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", currency='" + currency + '\'' +
                ", device=" + device +
                '}';
    }
}
