package us.gonet.rcpt.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.atm.FromAccount;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.rcpt.core.IReceiptBuilder;
import us.gonet.rcpt.core.ReceiptConstants;
import us.gonet.rcpt.core.utils.AsciiNonPrintable;
import us.gonet.rcpt.core.utils.UtilsRcpt;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.receipt.Receipt;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component( "receiptBuilder" )
public class ReceiptBuilder implements IReceiptBuilder {

    @Autowired
    UtilsRcpt utils;
    private ATMRequestModel wm;
    private ISO iso;
    private static final String CROSS = "************";
    private static final String ERROR = "Error al generar el recibo";


    @Override
    public Receipt build ( ATMRequestModel wm, ISO iso, String... str ) throws ServerException {
        this.wm = wm;
        this.iso = iso;
        Receipt receipt = new Receipt();
        receipt.setHeader( format( str[ 0 ] ) );
        receipt.setBody( format( str[ 1 ] ) );
        receipt.setTrailer( format( str[ 2 ] ) );
        return receipt;
    }

    private String format( String str ) throws ServerException{
        StringBuilder receipt = new StringBuilder();
        while ( str.length() > 0 ){
            int indexOf = str.indexOf( ',' );
            String str2 = str.substring( 0, indexOf );
            if ( str2.startsWith( "@" ) ) {
                receipt.append( textFormat( str2 ) );
            } else if ( str2.startsWith( "#" ) )
                receipt.append( variableFormat( str ) );
            else if ( str2.startsWith( "%" ) ){
                AsciiNonPrintable nonPrintable = AsciiNonPrintable.valueOf( str2.substring( 2, str2.length() -1 ) );
                receipt.append( ( char ) nonPrintable.getValue() );
            }
            str = str.substring( indexOf + 1 );
        }
        return receipt.toString();
    }

    private String textFormat( String str ){
        String text;
        str = str.substring( 1 );
        int indexOf = str.indexOf( '[' );
        text = str.substring( 0, indexOf );
        str = str.substring( indexOf + 1 );
        return utils.setSpaces( str, text );
    }

    private String variableFormat( String str ) throws ServerException {
        DateFormat dateFormat;
        DecimalFormat f = new DecimalFormat("$#,###,##0.00");
        str = str.substring( 1 );
        int indexOf = str.indexOf( '[' );
        String variable = "";
        try{
            switch ( ReceiptConstants.valueOf( str.substring( 0, indexOf ) ) ) {
                case DA:
                    dateFormat = new SimpleDateFormat( "dd/MM/yy" );
                    variable = dateFormat.format( new Date() );
                    break;
                case T1:
                    dateFormat = new SimpleDateFormat( "HH:mm" );
                    variable = dateFormat.format( new Date() );
                    break;
                case AT:
                    variable = wm.getTermId();
                    break;
                case TC:
                    variable = TranCodes.getAlphaValue( iso.getDataElements().get( 2 ).getContentField().substring( 0, 2 ) );
                    break;
                case OWNER:
                    variable = wm.getTermOwnerName();
                    break;
                case LOC:
                    variable = wm.getTermCity() + " " + wm.getTermState();
                    break;
                case PAN:
                    variable = utils.getP( iso.getDataElements().get( 34 ).getContentField() );
                    variable = CROSS + variable.substring( variable.length() -4 );
                    break;
                case AC:
                    variable = iso.getDataElements().get( 37 ).getContentField();
                    break;
                case FC:
                    variable = FromAccount.getAlphaValue( iso.getDataElements().get( 2 ).getContentField().substring( 2, 4 ) );
                    break;
                case OB:
                    float oldBalance = Float.parseFloat( iso.getDataElements().get( 43 ).getContentField().substring( 13, 25 ) ) / 100;
                    variable = f.format( oldBalance +  Float.parseFloat( iso.getDataElements().get( 3 ).getContentField() ) / 100 );
                    break;
                case AMT:
                    float surcharge;
                    float amount;
                    if ( ( surcharge = utils.getSurcharge( iso ) ) > 0 ){
                        amount = (  Float.parseFloat( iso.getDataElements().get( 3 ).getContentField() ) / 100 ) - surcharge;
                    }else {
                        amount = (  Float.parseFloat( iso.getDataElements().get( 3 ).getContentField() ) / 100 );
                    }
                    variable = f.format( amount );
                    break;
                case SRH:
                    variable = f.format( utils.getSurcharge( iso ) );
                    break;
                case IVA:
                    float iva;
                    if ( ( iva = utils.getSurcharge( iso ) ) > 0.00 ) {
                        variable = f.format( ( 16 * iva ) / 100 );
                    } else {
                        variable = f.format( iva );
                    }
                    break;
                case TT:
                    float total =  Float.parseFloat( iso.getDataElements().get( 3 ).getContentField() ) / 100;
                    variable = f.format( total );
                    break;
                case CB:
                    float currentBalance =  Float.parseFloat( iso.getDataElements().get( 43 ).getContentField().substring( 13, 25 ) ) / 100;
                    variable = f.format( currentBalance );
                    break;
                case AID:
                    if ( wm.getEmv() != null ){
                        variable = wm.getEmv().get( "9F06" );
                    }
                    break;
                case ARQC:
                    if ( wm.getEmv() != null ){
                        variable = wm.getEmv().get( "9F26" );
                    }
                    break;
                case ARPC:
                    variable = "N/D";
                    break;
                case PHN:
                    variable = wm.getPhoneNumber();
                    break;
                case COMP:
                    variable = wm.getCompany();
                    break;
                case STMT:
                    variable = utils.getSTMT( iso );
                    break;
                default:
                    List< ErrorWS > errors = new ArrayList <>();
                    ErrorWS error = new ErrorWS();
                    error.setCause( "RCPTRest-01" );
                    error.setErrorMessage( "Error al construir el Header" );
                    errors.add( error );
                    throw new ServerException( ERROR, errors );
            }
        }catch ( IllegalArgumentException e ){
            List< ErrorWS > errors = new ArrayList <>();
            ErrorWS error = new ErrorWS();
            error.setCause( "RCPTRest-02" );
            error.setErrorMessage( "Invalid LABEL" );
            errors.add( error );
            throw new ServerException( ERROR, errors );
        }
        try {
            if ( variable.equals( "" ) ){
                variable = "N/D";
            }
        }catch ( Exception e ){
            List< ErrorWS > errors = new ArrayList <>();
            ErrorWS error = new ErrorWS();
            error.setCause( "RCPTRest-03" );
            error.setErrorMessage( "Invalid data in message: " + str );
            errors.add( error );
            throw new ServerException( ERROR, errors );
        }
        str = str.substring( indexOf + 1 );
        return utils.setSpaces( str, variable );
    }
}