package us.gonet.jxiserver.model;

import java.beans.ConstructorProperties;

public class Tmk
{
    private String termType;
    private String atmRemote;
    private String atmLocal;

    public static TmkBuilder builder()
    {
        return new TmkBuilder();
    }

    public String getTermType() {
        return this.termType; }
    public String getAtmRemote() { return this.atmRemote; }
    public String getAtmLocal() { return this.atmLocal;
    }

    public void setTermType(String termType)
    {
        this.termType = termType; }
    public void setAtmRemote(String atmRemote) { this.atmRemote = atmRemote; }
    public void setAtmLocal(String atmLocal) { this.atmLocal = atmLocal; }
    public String toString() { return "Tmk(termType=" + getTermType() + ", atmRemote=" + getAtmRemote() + ", atmLocal=" + getAtmLocal() + ")"; }

    @ConstructorProperties({"termType", "atmRemote", "atmLocal"})
    public Tmk(String termType, String atmRemote, String atmLocal) { this.termType = termType; this.atmRemote = atmRemote; this.atmLocal = atmLocal;
    }

    public Tmk()
    {
    }
}