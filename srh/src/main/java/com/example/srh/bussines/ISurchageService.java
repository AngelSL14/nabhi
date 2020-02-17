package com.example.srh.bussines;

import com.example.srh.exeptionsutils.ResponseWrapper;
import com.example.srh.models.RequestSurchage;
import us.gonet.serverutils.model.jdb.SurchargeModel;

public interface ISurchageService {
     ResponseWrapper<SurchargeModel> sendSurchage(RequestSurchage s);
}
