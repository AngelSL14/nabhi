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
import us.gonet.serverutils.model.jdb.BinModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component("BinDB")
public class BinDB {

    private static final String ERROR = "Error el obtener la comision";

    @Autowired
    private UrlFilter rutils;

    @Autowired
    private SecurityServices securityServices;

    public BinModel sendSurchage (String bin ) throws ServerException {

            try{
                ResponseWrapper<BinModel> binResponse;
                RestTemplate restDB = new RestTemplate();

                UriTemplate uriTemplate = new UriTemplate(securityServices.getJpaUrl()+PostServices.BIN_DPROSA.getUrl()+"{bin}");
                URI url = uriTemplate.expand(bin);
                ParameterizedTypeReference<ResponseWrapper<BinModel>> rtatd = new ParameterizedTypeReference<ResponseWrapper<BinModel>>(){};
                ResponseEntity<ResponseWrapper<BinModel>> responseEntity = restDB.exchange(
                        rutils.getRequestEntity(url),rtatd);
                binResponse = responseEntity.getBody();
                    if(binResponse != null){
                        if(!binResponse.getCode().equals("00")){
                            return null;
                        }
                        else if(!binResponse.getBody().isEmpty()){
                            return binResponse.getBody().get(0);
                        }else{
                            throw new ServerException( ERROR , binResponse.getErrorWS() );
                        }
                    }else{
                        throw new ServerException( ERROR , Utils.buildErrors());
                    }
            }catch (ResourceAccessException e){
                List<ErrorWS> wsList = new ArrayList<>();
                wsList.add(new ErrorWS("-SRH01","Servicio DPROSA no disponible"));
                throw new ServerException(ERROR,wsList);
            }catch (HttpClientErrorException | HttpServerErrorException e){
                List<ErrorWS> errorWS = new ArrayList<>();
                errorWS.add(new ErrorWS("SRH02","Servicio surcharge no disponible"));
                throw new ServerException(ERROR,errorWS);
            }
    }
}
