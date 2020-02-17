package us.gonet.serverutils.model.jdb.esq;

public class CasseteSingle {

    public int getDispensados () {
        return dispensados;
    }

    public void setDispensados ( int dispensados ) {
        this.dispensados = dispensados;
    }

    public int getRetract () {
        return retract;
    }

    public void setRetract ( int retract ) {
        this.retract = retract;
    }
    @Override
    public String toString () {
        return "CasseteSingle{" +
                "numberCasetero=" + numberCasetero +
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
                '}';
    }

    private int decremento;
    private int numberCasetero;
    private int dispensados;
    private int incremento;
    private int denominacion;
    private int depositados;
    private String status;
    private int actual;
    private int presentados;
    private int retract;
    private String type;
    private int reject;
    private String currency;

    public int getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(int denominacion) {
        this.denominacion = denominacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPresentados() {
        return presentados;
    }

    public void setPresentados(int presentados) {
        this.presentados = presentados;
    }
    public int getActual () {
        return actual;
    }

    public void setActual ( int actual ) {
        this.actual = actual;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getIncremento () {
        return incremento;
    }

    public void setIncremento ( int incremento ) {
        this.incremento = incremento;
    }
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getNumberCasetero () {
        return numberCasetero;
    }

    public void setNumberCasetero ( int numberCasetero ) {
        this.numberCasetero = numberCasetero;
    }



    public int getDecremento () {
        return decremento;
    }

    public void setDecremento ( int decremento ) {
        this.decremento = decremento;
    }





    public int getDepositados () {
        return depositados;
    }

    public void setDepositados ( int depositados ) {
        this.depositados = depositados;
    }

    public int getReject () {
        return reject;
    }

    public void setReject ( int reject ) {
        this.reject = reject;
    }





}
