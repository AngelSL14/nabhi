package com.example.srh.core.rest.impl.dprosa;

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
import us.gonet.serverutils.model.jdb.SurchargeModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component("SurchargeDB")
public class SurchargeDB {

    private static final String ERROR = "Error el obtener la comision";

    @Autowired
    private UrlFilter rutils;

    @Autowired
    private SecurityServices securityServices;

    public SurchargeModel sendSurchage (String atd , String bin  , String sc) throws ServerException {

        try{
            ResponseWrapper<SurchargeModel> response;
            RestTemplate restDB = new RestTemplate();
            UriTemplate uriTemplate = new UriTemplate(securityServices.getJpaUrl()+"/surcharge/{atd}/{bin}/{sc}");
            URI url = uriTemplate.expand(atd, bin, sc);
            ParameterizedTypeReference<ResponseWrapper<SurchargeModel>> rtatd = new ParameterizedTypeReference<ResponseWrapper<SurchargeModel>>(){};
             ResponseEntity<ResponseWrapper<SurchargeModel>> responseEntity = restDB.exchange(
                     rutils.getRequestEntity(url), rtatd);
            response = responseEntity.getBody();
            if(response != null){
                if(!response.getCode().equals("00")){
                    return null;
                }
                else if(!response.getBody().isEmpty()){
                    return response.getBody().get(0);
                }else{
                    throw new ServerException( ERROR , response.getErrorWS() );
                }
            }else{
                throw new ServerException( ERROR , Utils.buildErrors());
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
