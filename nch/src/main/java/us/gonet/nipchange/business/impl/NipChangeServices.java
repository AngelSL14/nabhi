package us.gonet.nipchange.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.nipchange.business.INipChangeServices;
import us.gonet.nipchange.core.i8583.IResponseHandler;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;

@Component ( "nipChangeServices" )
public class NipChangeServices implements INipChangeServices {

    @Autowired
    @Qualifier ( "responseHandler" )
    IResponseHandler responseHandler;

    @Override
    public ResponseWrapper< ATMResponseModel > sendNipChange( ATMRequestModel atmRequestModel ) {
        return responseHandler.sendMessage( atmRequestModel );
    }
}
