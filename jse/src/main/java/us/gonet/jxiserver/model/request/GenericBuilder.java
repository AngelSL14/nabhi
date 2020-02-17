package us.gonet.jxiserver.model.request;

import java.util.Map;

public class GenericBuilder
{
    protected String termId;
    protected String ip;
    protected String txCommission;
    protected String tipoCuenta;
    protected String nip;
    protected String track;
    protected Map<String, String> emv;

    public GenericBuilder withTermId(String termId){this.termId = termId; return this; }
    public GenericBuilder withTxCommission(String txCommission) { this.txCommission = txCommission; return this; }
    public GenericBuilder withTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; return this; }
    public GenericBuilder withNip(String nip) { this.nip = nip; return this; }
    public GenericBuilder withIp(String ip) { this.ip = ip; return this; }
    public GenericBuilder withTrack(String track) { this.track = track; return this; }
    public GenericBuilder withEmv(Map<String, String> emv) { this.emv = emv; return this; }
    public Generic build() { return new Generic(this.termId, this.ip, this.txCommission, this.tipoCuenta, this.nip, this.track, this.emv); }

}