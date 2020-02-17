package us.gonet.jxiserver.model.request;

import java.util.Map;

public class CashWithdrawalModel extends Generic {
    private String cashWithAmount;
    public String getCashWithAmount() {
        return cashWithAmount;
    }

    public void setCashWithAmount(String cashWithAmount) {
        this.cashWithAmount = cashWithAmount;
    }

    public CashWithdrawalModel() {
    }

    CashWithdrawalModel(String termId, String ip, String txCommission, String tipoCuenta, String nip, String track, Map<String, String> emv, String cashWithAmount) {
        super(termId, ip, txCommission, tipoCuenta, nip, track, emv);
        this.cashWithAmount = cashWithAmount;
    }

    public static CashWithdrawalBuilder builder()
    {
        return new CashWithdrawalBuilder();
    }

    @Override
    public String toString() {
        return super.toString() + " CashWithdrawalModel{" +
                "cashWithAmount='" + cashWithAmount + '\'' +
                '}';
    }
}
