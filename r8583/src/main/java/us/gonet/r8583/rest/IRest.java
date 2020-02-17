package us.gonet.r8583.rest;

import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.node.NodeSingleModel;
import us.gonet.serverutils.model.receipt.Receipt;

public interface IRest {

    Receipt sendReceiptRequest ( ATMRequestModel ar, String message ) throws ServerException;
    String translatePinBlock ( String currentPinBlock, String track2, String termID, String termType, NodeSingleModel ns ) throws ServerException;
    ISO sendMessage ( ISO isoRequest ) throws ServerException;
}
