package us.gonet.iso.core.utils;

import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.iso.ServicesInformation;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

    private Utilities () {}

    public static String arrayByteToString ( byte[] b ) {
        StringBuilder s = new StringBuilder();
        for ( byte aB : b ) {
            char c = ( char ) aB;
            s.append( c );
        }
        return s.toString();
    }

    public static ResponseWrapper< ServicesInformation > setResponseError( ResponseWrapper< ServicesInformation > responseWrapper, String response, String code ){
        List< ServicesInformation > body = new ArrayList <>();
        List< ErrorWS > errors = new ArrayList<>();
        ErrorWS error = new ErrorWS();
        error.setCause( code );
        error.setErrorMessage ( response );
        errors.add( error );
        responseWrapper.setErrorWS( errors );
        responseWrapper.setBody( body );
        return responseWrapper;
    }

}
