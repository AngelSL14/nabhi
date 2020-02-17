package us.gonet.iso.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.iso.business.IISOServices;
import us.gonet.iso.config.ConfigProperties;
import us.gonet.iso.core.handler.MessageHandler;
import us.gonet.iso.core.utils.Errors;
import us.gonet.iso.core.utils.Utilities;
import us.gonet.iso8583.constants.atm.ResponseCodes;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.iso.ServicesInformation;

import java.util.ArrayList;
import java.util.List;

import static us.gonet.iso.core.utils.Errors.*;

@Component ( "isoservices" )
public class ISOServices implements IISOServices {

    @Autowired
    MessageHandler mh;

    @Autowired
    ConfigProperties configProperties;

    @Override
    public ResponseWrapper< ServicesInformation > sendLogon () {
        ResponseWrapper< ServicesInformation > sendLogon = new ResponseWrapper<>();
        ServicesInformation servicesInformation = new ServicesInformation();
        String response = mh.sendLogon();
        if ( response.equals( LOGON_COMPLETE.getValue() ) ) {
            List< ServicesInformation > body = new ArrayList<>();
            servicesInformation.setCode( LOGON_COMPLETE.getCode() );
            servicesInformation.setMessage( LOGON_COMPLETE.getValue() );
            body.add( servicesInformation );
            sendLogon.setCode( ResponseCodes.APPROVED.getValue() );
            sendLogon.setBody( body );
        } else {
            sendLogon.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            Utilities.setResponseError( sendLogon, response, Errors.getCodeForValue( response ) );
        }

        return sendLogon;
    }

    @Override
    public ResponseWrapper< ServicesInformation > sendLogoff () {
        ResponseWrapper< ServicesInformation > sendLogOff = new ResponseWrapper<>();
        ServicesInformation servicesInformation = new ServicesInformation();
        String response = mh.sendLogoff();
        if ( response.equals( LOGOFF_COMPLETE.getValue() ) ) {
            List< ServicesInformation > body = new ArrayList<>();
            servicesInformation.setCode( LOGON_COMPLETE.getCode() );
            servicesInformation.setMessage( LOGOFF_COMPLETE.getValue() );
            sendLogOff.setCode( ResponseCodes.APPROVED.getValue() );
            body.add( servicesInformation );
            sendLogOff.setBody( body );
        } else {
            sendLogOff.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            Utilities.setResponseError( sendLogOff, response, Errors.getCodeForValue( response ) );
        }
        return sendLogOff;
    }

    @Override
    public ResponseWrapper< ServicesInformation > statusSocket () {
        ResponseWrapper< ServicesInformation > statusSocket = new ResponseWrapper<>();
        ServicesInformation servicesInformation = new ServicesInformation();
        boolean status = mh.statusSocket();
        List< ServicesInformation > body = new ArrayList<>();
        if ( status ) {
            servicesInformation.setCode( SOCKET_OPEN.getCode() );
            servicesInformation.setMessage( SOCKET_OPEN.getValue() );
            statusSocket.setCode( ResponseCodes.APPROVED.getValue() );
            body.add( servicesInformation );
        } else {
            statusSocket.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            Utilities.setResponseError( statusSocket, SOCKET_STATUS_OFF.getValue(), Errors.getCodeForValue( SOCKET_STATUS_OFF.getValue() ) );
        }
        statusSocket.setBody( body );
        return statusSocket;
    }

    @Override
    public ResponseWrapper< ServicesInformation > turnOnSocket () {
        ResponseWrapper< ServicesInformation > turnOnSocket = new ResponseWrapper<>();
        ServicesInformation servicesInformation = new ServicesInformation();
        String response = mh.turnOnSocket();
        if ( response.equals( SOCKET_OPEN.getValue() ) ) {
            List< ServicesInformation > body = new ArrayList<>();
            servicesInformation.setCode( SOCKET_OPEN.getCode() );
            servicesInformation.setMessage( SOCKET_OPEN.getValue() );
            turnOnSocket.setCode( ResponseCodes.APPROVED.getValue() );
            body.add( servicesInformation );
            turnOnSocket.setBody( body );
        } else {
            turnOnSocket.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            Utilities.setResponseError( turnOnSocket, response, Errors.getCodeForValue( response ) );
        }
        return turnOnSocket;
    }

    @Override
    public ResponseWrapper< ServicesInformation > turnOffSocket () {
        ResponseWrapper< ServicesInformation > turnOffSocket = new ResponseWrapper<>();
        ServicesInformation servicesInformation = new ServicesInformation();
        String response = mh.turnOffSocket();
        if ( response.equals( SOCKET_CLOSE.getValue() ) ) {
            List< ServicesInformation > body = new ArrayList<>();
            servicesInformation.setCode( SOCKET_CLOSE.getCode() );
            servicesInformation.setMessage( SOCKET_CLOSE.getValue() );
            turnOffSocket.setCode( ResponseCodes.APPROVED.getValue() );
            body.add( servicesInformation );
            turnOffSocket.setBody( body );
        } else {
            turnOffSocket.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            Utilities.setResponseError( turnOffSocket, response, Errors.getCodeForValue( response ) );
        }
        return turnOffSocket;
    }

    @Override
    public ResponseWrapper< ServicesInformation > echo () {
        ResponseWrapper< ServicesInformation > echo = new ResponseWrapper<>();
        ServicesInformation servicesInformation = new ServicesInformation();
        String response = mh.sendEcho();
        if ( response.equals( ECHO.getValue() ) ) {
            List< ServicesInformation > body = new ArrayList<>();
            servicesInformation.setCode( ECHO.getCode() );
            servicesInformation.setMessage( ECHO.getValue() );
            echo.setCode( ECHO.getCode() );
            body.add( servicesInformation );
            echo.setBody( body );
        } else {
            echo.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            Utilities.setResponseError( echo, response, Errors.getCodeForValue( response ) );
        }
        return echo;
    }

    @Override
    public ResponseWrapper< ISO > sampleService ( ISO iso ) {
        ResponseWrapper< ISO > test = new ResponseWrapper<>();
        test.setCode( "96" );
        List< ErrorWS > errors = new ArrayList<>();
        errors.add( new ErrorWS( "ISO-15", "Un support operation" ) );
        test.setErrorWS( errors );
        return test;
    }


    @Override
    public ResponseWrapper< ISO > sendRequest ( ISO iso ) {
        ResponseWrapper< ISO > sendRequest = new ResponseWrapper<>();
        List< ErrorWS > errors = new ArrayList<>();
        ErrorWS error = new ErrorWS();
        List< ISO > isos = new ArrayList<>();
        errors.add( error );
        sendRequest.setBody( isos );
        if ( ! mh.statusSocket() ) {
            error.setCause( SOCKET_OFF.getCode() );
            error.setErrorMessage( SOCKET_OFF.getValue() );
            sendRequest.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            sendRequest.setErrorWS( errors );
        } else if ( ! mh.statusLog() ) {
            sendRequest.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            error.setErrorMessage( LOGOFF.getValue() );
            error.setCause( LOGOFF.getCode() );
            sendRequest.setErrorWS( errors );
        } else {
            ISO isoResponse = mh.sendMessage( iso );
            if ( ! isoResponse.isTimeOut() ) {
                if ( ! isoResponse.getHeader().get( 3 ).getContentField().equals( "000" ) ) {
                    sendRequest.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
                    error.setCause( isoResponse.getHeader().get( 3 ).getContentField() );
                    error.setErrorMessage( "Error en el data element: " + sendRequest.getCode() );
                    sendRequest.setErrorWS( errors );
                } else {
                    sendRequest.setCode( isoResponse.getDataElements().get( 38 ).getContentField() );
                }
                isos.add( isoResponse );
            } else {
                sendRequest.setCode( ResponseCodes.ISSUER_INOPERATIVE.getValue() );
                error.setCause( Errors.BANK_PROBLEM.getCode() );
                error.setErrorMessage( Errors.BANK_PROBLEM.getValue() );
                sendRequest.setErrorWS( errors );
            }
        }
        return sendRequest;
    }

}
