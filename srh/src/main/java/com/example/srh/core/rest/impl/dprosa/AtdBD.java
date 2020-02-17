package com.example.srh.core.rest.impl.dprosa;

import com.example.srh.core.rest.PostServices;
import com.example.srh.exeptionsutils.ErrorWS;
import com.example.srh.exeptionsutils.ResponseWrapper;
import com.example.srh.exeptionsutils.ServerException;
import com.example.srh.exeptionsutils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import us.gonet.security.rest.SecurityServices;
import us.gonet.serverutils.model.jdb.ATD;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component("ATDDB")
public class AtdBD {

    private static final String ERROR = "Error el obtener la comision";

    @Autowired
    private SecurityServices securityServices;

    @Autowired
    private UrlFilter rutils;
    public ATD sendSurchage (String ip ) throws ServerException {

        try{
            ResponseWrapper<ATD> response;
            RestTemplate restDB = new RestTemplate();
            UriTemplate uriTemplate = new UriTemplate(securityServices.getJpaUrl()+PostServices.ATD_DPROSA.getUrl()+"{ip}");
            URI url = uriTemplate.expand(ip);
            ParameterizedTypeReference<ResponseWrapper<ATD>> rtatd = new ParameterizedTypeReference<ResponseWrapper<ATD>>(){};
            ResponseEntity<ResponseWrapper<ATD>> responseEntity = restDB.exchange(rutils.getRequestEntity(url), rtatd);
            response = responseEntity.getBody();
            if(response == null){
                throw new ServerException( ERROR , Utils.buildErrors());
            }else{
                if(!response.getCode().equals("00")){
                    return null;
                }
                else if(!response.getBody().isEmpty()){
                    return response.getBody().get(0);
                }else{
                    throw new ServerException( ERROR , response.getErrorWS() );
                }
            }
        }catch (ResourceAccessException e){
            List<ErrorWS> errors = new ArrayList<>();
            errors.add(new ErrorWS("-SRH01","Servicio DPROSA no disponible"));
            throw new ServerException(ERROR,errors);
        }catch (HttpClientErrorException | HttpServerErrorException e){
            List<ErrorWS> errors = new ArrayList<>();
            errors.add(new ErrorWS("SRH02","Servicio surcharge no disponible"));
            throw new ServerException(ERROR,errors);
        }
    }

}
