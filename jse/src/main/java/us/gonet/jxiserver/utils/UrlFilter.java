package us.gonet.jxiserver.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ServerException;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component()
public class UrlFilter {
    private static final String ERROR = "Error el obtener la comision";
    private static final String AUTH_HEADER = "Authorization";
    private static final Logger LOG = LoggerFactory.getLogger( UrlFilter.class );

    @Autowired
    private AuthIn authIn;

    public RequestEntity getRequestEntity(URI url, String service, String method, Object bodyParams) throws ServerException {

        if(urlWhiteList(url))
        {
            switch(method)
            {
                case "get":
                    return RequestEntity
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(AUTH_HEADER, getAuthHeader(service))
                        .build();
                case "post":
                    return RequestEntity
                            .post(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .header(AUTH_HEADER, getAuthHeader(service))
                            .body(bodyParams);
                case "put":
                    return RequestEntity
                            .put(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .header(AUTH_HEADER, getAuthHeader(service))
                            .body(bodyParams);
                    default:
                        List<ErrorWS> errorWS = new ArrayList<>();
                        errorWS.add(new ErrorWS("JXI099", "Metodo Rest Invalido"));
                        throw new ServerException("JXI099", errorWS);
            }

        }
        else
        {
            List<ErrorWS> errs = new ArrayList<>();
            errs.add(new ErrorWS("JXI100", "Invalid URL"));
            throw new ServerException(ERROR, errs);
        }
    }

    private boolean urlWhiteList(URI url)
    {
        return url.getScheme().startsWith("http");

    }

    private String getAuthHeader(String service) throws ServerException {
        switch(service)
        {
            case "adp":
                return authIn.getTokenADPServices();
            case "ges":
                return authIn.getTokenGESServices();
            case "inq":
                return authIn.getTokenINQServices();
            case "jke":
                return authIn.getTokenJkeServices();
            case "iso":
                return authIn.getTokenIsoServices();
            case "nch":
                return authIn.getTokenNCHServices();
            case "stmt":
                return authIn.getTokenSTMTServices();
            case "wdl":
                return authIn.getTokenWDLServices();
            case "jdb":
                return authIn.getTokenJDBServices();
            case "srh":
                return authIn.getTokenSRHServices();
            case "reversal":
                return authIn.getTokenREVServices();
            default:
                List<ErrorWS> errors = new ArrayList<>();
                errors.add(new ErrorWS("JXI098", "Token de servicio no soportado"));
                throw new ServerException("JXI098", errors);
        }
    }

    public String sanitizeString(String val) throws ServerException {
        String regex = "[a-zA-Z0-9.=]+";

        if(val != null && Pattern.matches(regex, val) )
        {
            String newVal = Pattern.quote(val);
            return newVal.substring(2, newVal.length()-2);
        }
        else
        {
            List<ErrorWS> errs = new ArrayList<>();
            errs.add(new ErrorWS("JXI095", "Invalid Body Parameter"));
            LOG.error("Invalid Body Parameter");
            throw new ServerException("JXI095", errs);
        }


    }

    public Map<String, String> sanitizeEmv(Map<String, String> emv) throws ServerException {
        Map<String, String> emvSan = new HashMap<>();
        for(Map.Entry e : emv.entrySet())
        {
            emvSan.put((String)e.getKey(), sanitizeString((String)e.getValue()));
        }
        return emvSan;
    }



}
