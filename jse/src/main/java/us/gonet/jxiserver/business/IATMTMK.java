package us.gonet.jxiserver.business;

import us.gonet.jxiserver.model.request.Generic;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.jke.TmkEntity;


public interface IATMTMK {
    ResponseWrapper<TmkEntity> atmtmkRequest(Generic atmIp);
}
