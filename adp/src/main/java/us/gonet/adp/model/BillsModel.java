package us.gonet.adp.model;

public class BillsModel {
    private String bills;

    public BillsModel() {
    }

    public BillsModel(String bills) {
        this.bills = bills;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillsModel that = (BillsModel) o;

        return bills != null ? bills.equals(that.bills) : that.bills == null;
    }

    @Override
    public int hashCode() {
        return bills != null ? bills.hashCode() : 0;
    }
}
