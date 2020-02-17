package com.dksits.jke.business.impl;

import com.dksits.jke.business.ISocketServices;
import com.dksits.jke.core.handler.MessageHandler;
import com.dksits.jke.core.message.*;
import com.dksits.jke.core.rest.TCPNode;
import com.dksits.jke.core.util.Utilities;
import com.dksits.jke.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;

import java.util.ArrayList;
import java.util.List;

import static com.dksits.jke.exceptionutils.Errors.*;

@Component("jkeser")
public class SocketServices implements ISocketServices {

    @Autowired
    TCPNode tcpNode;

    @Autowired
    @Qualifier( "messageHandler" )
    MessageHandler mh;

    private static final String ERROR = "Error con el servicio Base 24 o los parametros enviados";
    private static final String SYSTEM_FAIL = "96";
    private static final String OK = "00";
    private List< ErrorWS > errors;
    private ErrorWS errorWs;

    @Override
    public ResponseWrapper< ServiceInfo > onSocket( ServiceInfo serviceInfo) {
        ResponseWrapper< ServiceInfo > onSocket = new ResponseWrapper<>();
        if (serviceInfo.getOperation().equals("activarSocket")) {
            String response = mh.turnOnSocket();
            if ( response.equals( SOCKET_OPEN.getValue() ) ){
                List< ServiceInfo > body = new ArrayList <>();
                serviceInfo.setMessage( SOCKET_OPEN.getValue() );
                body.add( serviceInfo );
                onSocket.setCode ( OK );
                onSocket.setBody( body );
            }else {
                onSocket.setCode ( SYSTEM_FAIL );
                onSocket = Utilities.setResponseError(response  );
            }
        }
        return onSocket;
    }

    @Override
    public ResponseWrapper< ServiceInfo >  offSocket(ServiceInfo serviceInfo) {
        ResponseWrapper< ServiceInfo >  offSocket = new ResponseWrapper<>();
        if (serviceInfo.getOperation().equals("desactivarSocket")) {
            String response = mh.turnOffSocket();
            if ( response.equals( SOCKET_CLOSE.getValue() ) ){
                List< ServiceInfo > body = new ArrayList <>();
                serviceInfo.setMessage( SOCKET_CLOSE.getValue() );
                body.add( serviceInfo );
                offSocket.setCode ( OK );
                offSocket.setBody( body );
            }else {
                offSocket.setCode( SYSTEM_FAIL );
                offSocket = Utilities.setResponseError(  response );
            }
        }
        return offSocket;
    }

    @Override
    public ResponseWrapper< ServiceInfo > statusSocket(ServiceInfo serviceInfo) {
        ResponseWrapper< ServiceInfo > statusSocket = new ResponseWrapper<>();
        if (serviceInfo.getOperation().equals("statusSocket")) {
            boolean status = mh.status();
            List< ServiceInfo > body = new ArrayList <>();
            if ( status ) {
                serviceInfo.setMessage( SOCKET_OPEN.getValue() );
                statusSocket.setCode ( OK );
            } else {
                serviceInfo.setMessage( SOCKET_STATUS_OFF.getValue() );
                statusSocket = Utilities.setResponseError( SOCKET_STATUS_OFF.getValue() );
                statusSocket.setCode ( SYSTEM_FAIL );
            }
            body.add( serviceInfo );
            statusSocket.setBody( body );
        }
        return statusSocket;
    }
    @Override
    public Response sendMessage( TMK request) {
        Response response = null;
        if (request.getOperation().equals("TMK")) {
            KeyExchangeRequest kr = new KeyExchangeRequest(
                    Utilities.sanitize(request.getTermType()),
                    Utilities.sanitize(request.getAtmRemote()),
                    Utilities.sanitize(request.getAtmLocal()));
            response = mh.sendMessage(kr);
        } else if (request.getOperation().equals("PINBLOCK")) {
            TranslatePinBlockRequest tbr = new TranslatePinBlockRequest(
                    Utilities.sanitize(request.getTermType()),
                    Utilities.sanitize(request.getAtmRemote()),
                    Utilities.sanitize(request.getAtmLocal()),
                    Utilities.sanitize(request.getPinBlock()),
                    Utilities.sanitize(request.getTrack2()),
                    Utilities.sanitize(request.getIpk()));
            response = mh.sendMessage(tbr);
        }
        return response;
    }

    @Override
    public ResponseWrapper<TmkEntity> sendMessageTPK(TPK request) {
        ResponseWrapper<TmkEntity> sendRequest = new ResponseWrapper<>();
        List< TmkEntity > body = new ArrayList <>();
            KeyExchangeRequest krq = new KeyExchangeRequest(Utilities.sanitize(request.getTermType()),
                    Utilities.sanitize(request.getAtmRemote()),
                    Utilities.sanitize(request.getAtmLocal()));
        initialize(body, sendRequest);
        if ( ! mh.status() ) {
            sendRequest.setCode( SYSTEM_FAIL );
            errorWs.setCause ( SOCKET_OFF.getCode () );
            errorWs.setErrorMessage ( SOCKET_OFF.getValue() );
        } else {
            Response response = mh.sendMessage( krq );
            if ( response == null ){
                sendRequest.setCode ( SYSTEM_FAIL );
                setError(errorWs, "JKE-11" );
                return sendRequest;
            }
            KeyExchangeResponse ker = ( KeyExchangeResponse ) response;
            if ( ker.getError().equals( "0" ) ){
                sendRequest.setCode( OK );
                TmkEntity tmk = new TmkEntity();
                tmk.setMode( ker.getMODE() );
                tmk.setSequence( ker.getSequence() );
                body.add( tmk );
            }else {
                sendRequest.setCode ( SYSTEM_FAIL  );
                caseError(ker.getError());
            }
        }
        return sendRequest;
    }

    @Override
    public ResponseWrapper<String> sendMessagePinBlock( PinBlock request ) {
        ResponseWrapper<String> sendRequest = new ResponseWrapper<>();
        List< String > body = new ArrayList <>();
        String zpk;
        try {
            zpk = tcpNode.getZPK( request.getNodeSingle() );
        } catch ( ServerException e ) {
            sendRequest.setErrorWS( e.getErrors() );
            sendRequest.setCode( "96" );
            return sendRequest;
        }
        request.setIpk( zpk );
        TranslatePinBlockRequest tbr = new TranslatePinBlockRequest(
                Utilities.sanitize(request.getTermType()),
                Utilities.sanitize(request.getAtmRemote()),
                Utilities.sanitize(request.getAtmLocal()),
                Utilities.sanitize(request.getPinBlock()),
                Utilities.sanitize(request.getTrack2()),
                Utilities.sanitize(request.getIpk()));
        initialize(body, sendRequest);
        if ( ! mh.status() ) {
            errorWs.setCause ( SOCKET_OFF.getCode () );
            errorWs.setErrorMessage ( SOCKET_OFF.getValue() );
            sendRequest.setCode( SYSTEM_FAIL );
            sendRequest.setErrorWS( errors );
        } else {
            Response response = mh.sendMessage(tbr);
            if ( response == null ){
                sendRequest.setCode ( SYSTEM_FAIL );
                setError(errorWs, "JKE-11" );
                return sendRequest;
            }
            TranslatePinBlockResponse tpb = (TranslatePinBlockResponse) response;
            if ( tpb.gerError().equals( "0" ) ){
                sendRequest.setCode( OK );
                body.add( tpb.getBLOCK() );
            }else {
                sendRequest.setCode ( SYSTEM_FAIL );
                caseError(tpb.gerError());
            }

        }
        return sendRequest;
    }

    private void setError( ErrorWS error, String cause ){
        error.setCause ( cause );
        error.setErrorMessage ( ERROR );
    }

    private void caseError(String code){
        switch (code) {
            case "1":
                set1();
                break;
            case "2":
                set2();
                break;
            case "3":
                set3();
                break;
            case "4":
                set4();
                break;
            case "5":
                set5();
                break;
            case "6":
                set6();
                break;
            default:
                setDefault();
                break;
        }
    }

    private void initialize(List body, ResponseWrapper sendRequest){
        errors = new ArrayList <>();
        errorWs = new ErrorWS();
        errors.add(errorWs);
        sendRequest.setErrorWS( errors );
        sendRequest.setBody( body );
    }

    private void set1(){
        errorWs.setErrorMessage( "Device failed" );
        errorWs.setCause ( "ZPK-1" );
    }
    private void set2(){
        errorWs.setErrorMessage( "Error con la IPK" );
        errorWs.setCause ( "TPK-2" );
    }
    private void set3(){
        errorWs.setErrorMessage( "Invalid Data" );
        errorWs.setCause ( "ZPK-3" );
    }
    private void set4(){
        errorWs.setErrorMessage( "Invalid parameter" );
        errorWs.setCause ( "TPK-4" );

    }
    private void set5(){
        errorWs.setErrorMessage( "Sanity Check");
        errorWs.setCause ( "ZPK-5" );
    }
    private void set6(){
        errorWs.setErrorMessage("Database problem");
        errorWs.setCause ( "TPK-6" );
    }
    private void setDefault(){
        errorWs.setErrorMessage("Error -500");
        errorWs.setCause ( "ZPK-500" );
    }

}
