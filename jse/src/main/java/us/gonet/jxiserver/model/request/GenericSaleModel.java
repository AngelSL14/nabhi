package us.gonet.jxiserver.model.request;

import java.util.Map;

public class GenericSaleModel extends CashWithdrawalModel {
    protected String telefono;
    protected String company;

    public GenericSaleModel() {
    }

    public GenericSaleModel(String termId, String ip, String txCommission, String tipoCuenta, String nip, String track, Map<String, String> emv, String cashWithAmount, String telefono, String company) {
        super(termId, ip, txCommission, tipoCuenta, nip, track, emv, cashWithAmount);
        this.telefono = telefono;
        this.company = company;
    }

    public static GenericSaleBuilder builer()
    {
        return new GenericSaleBuilder();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return super.toString() +" GenericSaleModel{" +
                "telefono='" + telefono + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
