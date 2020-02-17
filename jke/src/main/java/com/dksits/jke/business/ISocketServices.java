package com.dksits.jke.business;

import com.dksits.jke.core.message.Response;
import com.dksits.jke.model.*;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;


public interface ISocketServices {

    ResponseWrapper onSocket( ServiceInfo serviceInfo);
    ResponseWrapper offSocket( ServiceInfo serviceInfo);
    ResponseWrapper statusSocket( ServiceInfo serviceInfo);
    Response sendMessage( TMK request );
    ResponseWrapper<TmkEntity> sendMessageTPK(TPK request );
    ResponseWrapper<String> sendMessagePinBlock( PinBlock request );

}
