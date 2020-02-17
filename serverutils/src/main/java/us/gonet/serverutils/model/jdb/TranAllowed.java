package us.gonet.serverutils.model.jdb;

public class TranAllowed {

    private String allowedCode;

    public String getAllowedCode() {
        return allowedCode;
    }

    public void setAllowedCode(String allowedCode) {
        this.allowedCode = allowedCode;
    }

    @Override
    public String toString() {
        return "TranAllowed{" +
                "allowedCode='" + allowedCode + '\'' +
                '}';
    }
}
