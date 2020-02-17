package us.gonet.jxiserver.dbprosa.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseWrapper<T> {

    private String code;
    private List<ErrorWS> errorWS;
    private List<T> body;

    public void addBody(T b)
    {
        if(body == null)
        {
            body = new ArrayList<>();
        }
        body.add(b);
    }

    public void addError(ErrorWS e)
    {
        if(errorWS == null)
        {
            errorWS = new ArrayList<>();
        }
        errorWS.add(e);
    }

    public void addAllError(List<ErrorWS> e)
    {
        if(errorWS == null)
        {
            errorWS = new ArrayList<>();
        }
        errorWS.addAll(e);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ErrorWS> getErrorWS() {
        return errorWS;
    }

    public void setErrorWS(List<ErrorWS> errorWS) {
        this.errorWS = errorWS;
    }

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseWrapper<?> that = (ResponseWrapper<?>) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (errorWS != null ? !errorWS.equals(that.errorWS) : that.errorWS != null) return false;
        return body != null ? body.equals(that.body) : that.body == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (errorWS != null ? errorWS.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "code='" + code + '\'' +
                ", errorWS=" + errorWS +
                ", body=" + body +
                '}';
    }
}
