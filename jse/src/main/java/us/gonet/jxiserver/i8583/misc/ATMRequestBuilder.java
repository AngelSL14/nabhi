package us.gonet.jxiserver.i8583.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.atm.ToAccount;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.model.request.CashWithdrawalModel;
import us.gonet.jxiserver.model.request.ChangeNipModel;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.request.GenericSaleModel;
import us.gonet.jxiserver.utils.TRAN_CDE;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.Apc;
import us.gonet.serverutils.model.jdb.NodeProsa;
import us.gonet.serverutils.model.node.NodeSingleModel;
import us.gonet.utils.Utilities;
@Component( "atmRequestBuilder" )
public class ATMRequestBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(ATMRequestBuilder.class);


    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    AuthIn authIn;

    @Autowired @Qualifier("isoUtils")
    ISOUtils isoUtils;

    public ATMRequestModel build(Generic e, ATD atd, Apc apc, String process ) throws ServerException {
        ATMRequestModel request = new ATMRequestModel();

        request.setFromAccount( e.getTipoCuenta() );
        request.setToAccount( ToAccount.NO_ACCOUNT.getValue() );
        if ( process.equals( TRAN_CDE.BALANCE_INQUIRY.getValue() )
                || process.equals( TRAN_CDE.PIN_CHANGE.getValue())
                || process.equals( TRAN_CDE.STATEMENT_PRINT.getValue()) ) {
            request.setAmount( "00" );
        } else {
            if( process.equals( TRAN_CDE.WITHDRAWAL.getValue() ))
            {
                request.setAmount( ((CashWithdrawalModel) e).getCashWithAmount() );
            }

            else if(process.equals( TRAN_CDE.GENERIC_SALE.getValue() ))
            {
                request.setAmount( ((GenericSaleModel) e).getCashWithAmount() );
            }
        }

        request.setEntryMode( "051" );
        request.setSurcharge( isoUtils.surchargeValue(e.getIp(), e.getTrack(), process) );
        request.setTrack2( e.getTrack() );

        ResponseWrapper<NodeProsa> nodeResponse = new ResponseWrapper<>();
        try {
            nodeResponse = restRequestFactory.buildRestRequest("/nodeProsa/getTraceNumber/{idNode}",
                NodeProsa.class, "jdb", "No se encontro trace number",
                    "get", "", atd.getNodeProsa().getIdNode() );
        } catch (ServerException e1) {
            LOG.error(e1.getErrors().toString());
        }
        NodeProsa resp = nodeResponse.getBody().get(0);
        String traceNumber = Utilities.leftPadding("0", 6, String.valueOf(resp.getTracerNumber()));

        ResponseWrapper<ATD> atdResponse = new ResponseWrapper<>();
        try {
            atdResponse = restRequestFactory.buildRestRequest("/atd/sequence/{ip}",
                    ATD.class, "jdb", "No se encontro sequence number",
                    "get", "", e.getIp());
        } catch (ServerException e1) {
            LOG.error(e1.getErrors().toString());
        }
        ATD atdResp = atdResponse.getBody().get(0);
        String sequenceNumber = Utilities.leftPadding("0", 12, String.valueOf(atdResp.getSequenceNumber()));

        request.setTraceNumber( traceNumber );
        request.setSequenceNumber( sequenceNumber );


        request.setTermId( Utilities.rightPadding(" ", 16, atd.getTerminalId()) );

        request.setTermOwnerName(Utilities.rightPadding(" ", 22, atd.getIdf().getName()));
        request.setTermCity(  Utilities.rightPadding(" ", 13, atd.getCounty().getCountyName()) );
        request.setTermState( Utilities.rightPadding(" ", 3, atd.getCounty().getState().getStateShortName()) );
        request.setTermCountry( atd.getIdf().getCountry().getAlpha2() );
        request.setGroupAllow( isoUtils.allowGroup( atd, apc ) );
        request.setCurrencyCode( atd.getIdf().getCountry().getCountryCode() );
        request.setPinBlock( e.getNip() );
        if ( process.equals( TRAN_CDE.PIN_CHANGE.getValue() ) ) {
            request.setNewPinBlock( ((ChangeNipModel) e).getNewPin() );
            request.setNewPinBlock2( ((ChangeNipModel) e).getConfirmNewPin() );
        }
        request.setTermFiid( atd.getIdf().getFiid() );
        request.setlNet( atd.getIdf().getLogicalNet() );

        request.setTimeOffSet( isoUtils.verifyUTCDifference( atd.getCounty().getState().getZone() ) );
        request.setEmv( e.getEmv() );
        request.setTermType( atd.getDeviceType() );

        if ( process.equals( TRAN_CDE.GENERIC_SALE.getValue() ) ) {
            request.setCompany( ((GenericSaleModel) e).getCompany() );
            request.setPhoneNumber( ((GenericSaleModel) e).getTelefono() );
            request.setPhoneNumber2( ((GenericSaleModel) e).getTelefono() );
        }

        NodeSingleModel node = NodeSingleModel.builder().nodeName(atd.getNodeProsa().getNodeName())
                .idNode(atd.getNodeProsa().getIdNode()).build();
        request.setNodeSingle(node);

        return request;
    }
}
