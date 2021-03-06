package us.gonet.balance.core.i8583;

import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;

public interface IISOBuilder {

    ISO buildMessage ( ATMRequestModel rm ) throws ServerException;
}
