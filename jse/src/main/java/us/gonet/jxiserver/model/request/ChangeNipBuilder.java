package us.gonet.jxiserver.model.request;

public class ChangeNipBuilder extends GenericBuilder{
    private String newPin;
    private String confirmNewPin;

    public void withtNewPin(String newPin) {
        this.newPin = newPin;
    }

    public void withConfirmNewPin(String confirmNewPin) {
        this.confirmNewPin = confirmNewPin;
    }

    @Override
    public ChangeNipModel build()
    {
        return new ChangeNipModel(this.termId, this.ip, this.txCommission, this.tipoCuenta, this.nip, this.track, this.emv, this.newPin, this.confirmNewPin);
    }

}
