package us.gonet.jxiserver.i8583.rest;

import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.Apc;

public interface IRest {

    ATD getATD ( String ip ) throws ServerException;
    Apc getAPC ( String key ) throws ServerException;
    ATMResponseModel sendTransaction(TranCodes tranCode, ATMRequestModel atmRequestModel ) throws ServerException;

}
