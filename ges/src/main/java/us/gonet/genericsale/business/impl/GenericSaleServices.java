package us.gonet.genericsale.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.genericsale.business.IGenericSaleServices;
import us.gonet.genericsale.core.i8583.IResponseHandler;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;

@Component ( "genericSaleServices" )
public class GenericSaleServices implements IGenericSaleServices {

    @Autowired
    @Qualifier ( "responseHandler" )
    IResponseHandler responseHandler;

    @Override
    public ResponseWrapper< ATMResponseModel > sendGenericSale( ATMRequestModel atmRequestModel ) {
        return responseHandler.sendMessage( atmRequestModel );
    }
}
