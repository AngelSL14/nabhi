package us.gonet.jxiserver.dbprosa.response;

public class ErrorWS {

    private String cause;
    private String errorMessage;

    public ErrorWS() {
    }

    public ErrorWS(String cause, String errorMessage) {
        this.cause = cause;
        this.errorMessage = errorMessage;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorWS errorWS = (ErrorWS) o;

        if (cause != null ? !cause.equals(errorWS.cause) : errorWS.cause != null) return false;
        return errorMessage != null ? errorMessage.equals(errorWS.errorMessage) : errorWS.errorMessage == null;
    }

    @Override
    public int hashCode() {
        int result = cause != null ? cause.hashCode() : 0;
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ErrorWS{" +
                "cause='" + cause + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
