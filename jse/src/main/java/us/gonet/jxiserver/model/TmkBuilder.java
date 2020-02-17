package us.gonet.jxiserver.model;

public class TmkBuilder
{
    private String termType;
    private String atmRemote;
    private String atmLocal;

    public TmkBuilder withTermType(String termType)
    {
        this.termType = termType; return this; }
    public TmkBuilder withAmRemote(String atmRemote) { this.atmRemote = atmRemote; return this; }
    public TmkBuilder withAtmLocal(String atmLocal) { this.atmLocal = atmLocal; return this; }
    public Tmk build() { return new Tmk(this.termType, this.atmRemote, this.atmLocal); }
    public String toString() { return "Tmk.TmkBuilder(termType=" + this.termType + ", atmRemote=" + this.atmRemote + ", atmLocal=" + this.atmLocal + ")";
    }
}