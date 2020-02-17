package com.dksits.jke.exceptionutils;

import org.springframework.stereotype.Component;
import us.gonet.serverutils.exceptionutils.ErrorWS;

@Component ( "errorUtils" )

public class ErrorWSUtils {
    public ErrorWS createErrorLogin ( String cause, String errorMessage ) {
        ErrorWS errorWS = new ErrorWS();
        errorWS.setCause( cause );
        errorWS.setErrorMessage( errorMessage );
        return errorWS;
    }
}
