package us.gonet.jxiserver.model.request;


public class CashWithdrawalBuilder extends GenericBuilder {
    protected String cashWithAmount;

    public CashWithdrawalBuilder withCashWithAmount(String cashWithAmount) {
        this.cashWithAmount = cashWithAmount;
        return this;
    }

    @Override
    public CashWithdrawalModel build()
    {
        return new CashWithdrawalModel(this.termId, this.ip, this.txCommission, this.tipoCuenta, this.nip, this.track, this.emv, this.cashWithAmount);
    }

}
