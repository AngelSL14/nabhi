package com.example.srh.bussines.impl;

import com.example.srh.bussines.ISurchageService;
import com.example.srh.core.rest.impl.dprosa.AtdBD;
import com.example.srh.core.rest.impl.dprosa.BinDB;
import com.example.srh.core.rest.impl.dprosa.SurchargeDB;
import com.example.srh.exeptionsutils.ErrorWS;
import com.example.srh.exeptionsutils.ResponseWrapper;
import com.example.srh.exeptionsutils.ServerException;
import com.example.srh.models.RequestSurchage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.BinModel;
import us.gonet.serverutils.model.jdb.SurchargeModel;

import java.util.ArrayList;
import java.util.List;

@Component("ISurchageService")
public class SurchageServiceImp implements ISurchageService {

    @Autowired
    @Qualifier("BinDB")
    BinDB binDB;

    @Autowired
    @Qualifier("SurchargeDB")
    SurchargeDB surchargeDB;

    @Autowired
    @Qualifier("ATDDB")
    AtdBD atdBD;

    @Override
    public ResponseWrapper<SurchargeModel> sendSurchage(RequestSurchage s){
        boolean binFountIt = false;
        ResponseWrapper<SurchargeModel> response = new ResponseWrapper<>();
        try{
            String bin = s.getTrack().substring(0,7);
            BinModel b = null;
            while (!binFountIt){
                b = binDB.sendSurchage(bin);
                if(b!=null){
                    binFountIt=true;
                }else{
                    if(bin.length() == 1){
                        break;
                    }else {
                        bin = bin.substring(0,bin.length()-1);
                    }
                }
            }
            //DATOS EN DURO PARA EL CONSUMO DEL SURGARGE
            return defaultSurcharge(s, b);
        }catch (ServerException e){
            response.setCode("01");
            return response;
        }
    }

    private  ResponseWrapper<SurchargeModel> defaultSurcharge(RequestSurchage s,BinModel b ) throws ServerException {
        String binSr = "";

        ResponseWrapper<SurchargeModel> response = new ResponseWrapper<>();
        if(b!=null){
            binSr = b.getIdf().getFiid();
        }else{
            binSr = "****";
        }
        ATD atd =  atdBD.sendSurchage(s.getIp());
        if(atd !=null){
            SurchargeModel surcharge =  surchargeDB.sendSurchage(atd.getIdf().getFiid(),binSr,s.getTransactionCode());
            if(surcharge !=null){
                response.setCode("00");
                response.addBody(surcharge);
                return response;
            }else{
                surcharge =  surchargeDB.sendSurchage(atd.getIdf().getFiid(),"****",s.getTransactionCode());
                response.setCode("00");
                response.addBody(surcharge);
                return response;
            }
        }else{
            List<ErrorWS> errors = new ArrayList<>();
            errors.add(new ErrorWS("-SRH01","ATD No registrado"));
            throw new ServerException("Servicio atd No disponible",errors);
        }

    }
}
