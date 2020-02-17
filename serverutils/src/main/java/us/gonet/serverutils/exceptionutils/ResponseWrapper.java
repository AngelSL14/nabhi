package us.gonet.serverutils.exceptionutils;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class ResponseWrapper < T >{

    private String code;
    private List < ErrorWS > errorWS;
    private List < T > body;

    public void addBody ( T b ) {
        if ( body == null ) {
            body = new ArrayList <>();
        }
        body.add( b );
    }

    public void addError ( ErrorWS e ) {
        if ( errorWS == null ) {
            errorWS = new ArrayList <>();
        }
        errorWS.add( e );
    }

    public void addAllError ( List < ErrorWS > e ) {
        if ( errorWS == null ) {
            errorWS = new ArrayList <>();
        }
        errorWS.addAll( e );
    }

    public String getCode () {
        return code;
    }

    public void setCode ( String code ) {
        this.code = code;
    }


    public List < ErrorWS > getErrorWS () {
        return errorWS;
    }

    public void setErrorWS ( List < ErrorWS > errorWS ) {
        this.errorWS = errorWS;
    }

    public List < T > getBody () {
        return body;
    }

    public void setBody ( List < T > body ) {
        this.body = body;
    }

    @Override
    public String toString () {
        return "ResponseWrapper{" +
                "code='" + code + '\'' +
                ", errorWS=" + errorWS +
                ", body=" + body +
                '}';
    }
}
