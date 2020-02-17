package us.gonet.jxiserver.model.request;

public class GenericSaleBuilder extends CashWithdrawalBuilder{
    private String telefono;
    private String company;

    public GenericSaleBuilder withTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public GenericSaleBuilder withCompany(String company) {
        this.company = company;
        return this;
    }

    @Override
    public GenericSaleModel build()
    {
        return new GenericSaleModel(this.termId, this.ip, this.txCommission, this.tipoCuenta, this.nip, this.track, this.emv, this.cashWithAmount, this.telefono, this.company);
    }
}
