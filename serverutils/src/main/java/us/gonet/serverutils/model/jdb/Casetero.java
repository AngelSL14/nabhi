package us.gonet.serverutils.model.jdb;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Casetero {

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
    private String status;
    private int presentados;
    private String type;
    private String currency;
    private DeviceStatus device;

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

    public DeviceStatus getDevice() {
        return device;
    }

    public void setDevice(DeviceStatus device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Casetero{" +
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
                ", status='" + status + '\'' +
                ", presentados=" + presentados +
                ", type='" + type + '\'' +
                ", currency='" + currency + '\'' +
                ", device=" + device +
                '}';
    }
}
