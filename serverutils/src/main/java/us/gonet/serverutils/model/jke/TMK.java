package us.gonet.serverutils.model.jke;

import com.google.gson.GsonBuilder;

public class TMK {

    private String operation;
    private String termType;
    private String atmRemote;
    private String atmLocal;
    private String pinBlock;
    private String track2;
    private String ipk;
    private String sequence;

    public String getOperation(){
        return operation;
    }

    public void setOperation(String operation){
        this.operation=operation;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getAtmRemote() {
        return atmRemote;
    }

    public void setAtmRemote(String atmRemote) {
        this.atmRemote = atmRemote;
    }

    public String getAtmLocal() {
        return atmLocal;
    }

    public void setAtmLocal(String atmLocal) {
        this.atmLocal = atmLocal;
    }

    public String getPinBlock() {
        return pinBlock;
    }

    public void setPinBlock(String pinBlock) {
        this.pinBlock = pinBlock;
    }

    public String getTrack2() {
        return track2;
    }

    public void setTrack2(String track2) {
        this.track2 = track2;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
