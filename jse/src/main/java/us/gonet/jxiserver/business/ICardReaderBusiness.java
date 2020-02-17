package us.gonet.jxiserver.business;

import us.gonet.jxiserver.model.request.AtmInfo;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.response.TarjetaInfo;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;

public interface ICardReaderBusiness
{
    ResponseWrapper<Generic> incomingCard(Generic generic);
    ResponseWrapper<TarjetaInfo> validatingCard(AtmInfo atmInfo);
}
