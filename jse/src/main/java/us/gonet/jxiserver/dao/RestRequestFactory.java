package us.gonet.jxiserver.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import us.gonet.jxiserver.utils.UrlBuilder;
import us.gonet.jxiserver.utils.UrlFilter;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class RestRequestFactory {

    @Autowired
    Gson gson;

    @Autowired
    private UrlBuilder urlBuilder;

    @Autowired
    private UrlFilter urlFilter;

    public ResponseWrapper buildRestRequest(String service, Class wrapperType, String propertiesClass, String errorMessage, String restMethod, Object bodyParams, Object... urlVar) throws ServerException
    {
        ResponseWrapper wrapper;
        RestTemplate rt = new RestTemplate();
        URI url;
        if(urlVar==null)
        {
            try {
                url = new URI(urlBuilder.buildUrl(service, propertiesClass));
            } catch (URISyntaxException e) {
                List<ErrorWS> errWs = new ArrayList<>();
                errWs.add(new ErrorWS("JXI097", "Invalid URL to Rest Petition"));
                throw new ServerException("JXI097", errWs);
            }
        }
        else
        {
            UriTemplate uriTemplate = new UriTemplate(urlBuilder.buildUrl(service, propertiesClass));
            url = uriTemplate.expand(urlVar);
        }

        ParameterizedTypeReference<LinkedHashMap> response = new ParameterizedTypeReference<LinkedHashMap>(){};
        ResponseEntity<LinkedHashMap> restResponse = null;
        restResponse = rt.exchange(urlFilter.getRequestEntity(url, propertiesClass, restMethod, bodyParams), response);

        wrapper = buildResponse(restResponse, wrapperType, errorMessage);
        return wrapper;

    }

    private ResponseWrapper buildResponse(ResponseEntity<LinkedHashMap> restResponse, Class type, String errorMessage) throws ServerException
    {

        ObjectMapper om = new ObjectMapper();
        ResponseWrapper responseWrapper = new ResponseWrapper();
        if(restResponse.getBody().get("code").equals("00"))
        {
            responseWrapper.setCode((String) restResponse.getBody().get("code"));
            for(LinkedHashMap m: (List<LinkedHashMap>)restResponse.getBody().get("body"))
            {
                Object tm = om.convertValue(m, type);
                responseWrapper.addBody(tm);
            }
        }
        else
        {
            List<ErrorWS> errorWS = new ArrayList<>();
            for(LinkedHashMap m: (List<LinkedHashMap>)restResponse.getBody().get("errorWS"))
            {
                Object error = om.convertValue(m, ErrorWS.class);
                errorWS.add((ErrorWS) error);
            }
            throw new ServerException(errorMessage, errorWS);
        }
        return responseWrapper;
    }


}


