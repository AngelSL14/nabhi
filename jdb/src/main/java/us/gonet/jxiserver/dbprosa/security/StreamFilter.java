package us.gonet.jxiserver.dbprosa.security;

import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.response.ErrorWS;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;

import java.util.regex.Pattern;

@Component
public class StreamFilter {
    public String sanitizeString(String val)  throws ServerException {
        String regex = "[a-zA-Z0-9.=* ]+";

        if(val != null && Pattern.matches(regex, val) )
        {
            String newVal = Pattern.quote(val);
            return newVal.substring(2, newVal.length()-2);
        }
        else
        {
            throw new ServerException("JDB095", null);
        }


    }

    public ResponseWrapper sanitizeError()
    {
        ResponseWrapper respose = new us.gonet.jxiserver.dbprosa.response.ResponseWrapper();
        respose.setCode("01");
        respose.addError(new ErrorWS("JDB095", "Parametro de entrada Invalido" ));
        return respose;
    }
}
