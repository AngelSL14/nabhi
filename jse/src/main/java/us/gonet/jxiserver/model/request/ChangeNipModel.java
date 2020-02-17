package us.gonet.jxiserver.model.request;

import java.util.Map;

public class ChangeNipModel extends Generic {
    private String newPin;
    private String confirmNewPin;

    public String getNewPin() {
        return newPin;
    }

    public void setNewPin(String newPin) {
        this.newPin = newPin;
    }

    public String getConfirmNewPin() {
        return confirmNewPin;
    }

    public void setConfirmNewPin(String confirmNewPin) {
        this.confirmNewPin = confirmNewPin;
    }

    public ChangeNipModel() {
    }

    public ChangeNipModel(String termId, String ip, String txCommission, String tipoCuenta, String nip, String track, Map<String, String> emv, String newPin, String confirmNewPin) {
        super(termId, ip, txCommission, tipoCuenta, nip, track, emv);
        this.newPin = newPin;
        this.confirmNewPin = confirmNewPin;
    }

    public static ChangeNipBuilder builder()
    {
        return new ChangeNipBuilder();
    }

    @Override
    public String toString() {
        return  super.toString() + " ChangeNipModel{" +
                "newPin='" + newPin + '\'' +
                ", confirmNewPin='" + confirmNewPin + '\'' +
                '}';
    }
}
