package us.gonet.adp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import us.gonet.adp.config.Properties;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbServices {

    @Autowired @Qualifier("props")
    private Properties properties;

    @Autowired
    private UrlFilter urlFilter;

    public List<Casetero> getCaseteros(String ip) throws ServerException {

        UriTemplate uriTemplate = new UriTemplate(
                properties.getProtocol() + properties.getHost() + ":"
                + properties.getPort() + properties.getPath()
                + "/caseteros/" + "{ip}");
        URI url = uriTemplate.expand(ip);
        System.err.println("UriTemplate in DbServices: " + url.toString());
        List<Casetero> response;
        RestTemplate rt = new RestTemplate();

        ParameterizedTypeReference<ResponseWrapper<Casetero>> rcaseteros = new ParameterizedTypeReference<ResponseWrapper<Casetero>>(){};
        ResponseEntity<ResponseWrapper<Casetero>> restResponse = rt.exchange(urlFilter.getRequestEntity(url), rcaseteros);
        if(restResponse.getBody() != null && restResponse.getBody().getCode().equals("00"))
        {
            response = restResponse.getBody().getBody();
        }
        else if(restResponse.getBody() != null && !restResponse.getBody().getCode().equals("00"))
        {

            throw new ServerException("Error en la busqueda en BD",restResponse.getBody().getErrorWS());
        }
        else
        {
            List<ErrorWS> errors = new ArrayList<>();
            errors.add(new ErrorWS());
            throw new ServerException("Error en la peticion a la BD",errors);
        }
        return response;
    }


}
