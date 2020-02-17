package us.gonet.serverutils.model.jdb;

public class BillsModel {

    private String bills;

    public String getBills() {
        return bills;
    }

    public void setBills(String bills) {
        this.bills = bills;
    }

    @Override
    public String toString() {
        return "BillsModel{" +
                "bills='" + bills + '\'' +
                '}';
    }
}
