package us.gonet.genericsale.core.i8583.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import us.gonet.genericsale.core.i8583.IISOBuilder;
import us.gonet.genericsale.core.i8583.TelephoneCompany;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.iso8583.message.Request0200;
import us.gonet.r8583.rest.IRest;
import us.gonet.r8583.rest.impl.jke.TranslatePinBlock;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.token.Token;
import us.gonet.token.Tokens;
import us.gonet.token.emv.TokenEmv;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Import ( { TranslatePinBlock.class} )
@Component ( "iSOBuilder" )
public class ISOBuilder implements IISOBuilder {

    private static final Logger LOG = LoggerFactory.getLogger( ISOBuilder.class );

    @Autowired
    @Qualifier( "translatePinBlock" )
    IRest translatePinBlock;

    public ISO buildMessage( ATMRequestModel wm ) throws ServerException {
        try {
            wm.setPinBlock( translatePinBlock.translatePinBlock( wm.getPinBlock(), wm.getTrack2(), wm.getTermId(), wm.getTermType(), wm.getNodeSingle()  ) );
        } catch ( ServerException e ) {
            throw new ServerException( "Error al construir el mensaje ISO-8583", e.getErrors() );
        }
        Map < String, String > dataElements = new LinkedHashMap <>();
        dataElements.put( "p3", TranCodes.GENERIC_SALE.getValue() + wm.getFromAccount() + wm.getToAccount() );
        DecimalFormat f = new DecimalFormat("#,###,##0.00" );
        float amount = Float.parseFloat( wm.getAmount() );
        if ( wm.getSurcharge() != null && ! wm.getSurcharge().isEmpty() ){
            amount += Float.parseFloat( wm.getSurcharge() );
        }
        dataElements.put( "p4", f.format( amount ) );
        dataElements.put( "p11", wm.getTraceNumber() );
        dataElements.put( "p22", wm.getEntryMode() );
        dataElements.put( "p28", wm.getSurcharge() );
        dataElements.put( "p32", wm.getTermFiid().substring( 1 ) );
        dataElements.put( "p35", wm.getTrack2() );
        dataElements.put( "p37", wm.getSequenceNumber() );
        dataElements.put( "p41", wm.getTermId() );
        dataElements.put( "p43", wm.getTermOwnerName() + wm.getTermCity() + wm.getTermState() + wm.getTermCountry() );
        dataElements.put( "p48", wm.getGroupAllow() );
        dataElements.put( "p49", wm.getCurrencyCode() );
        dataElements.put( "p52", wm.getPinBlock() );
        dataElements.put( "p60", wm.getTermFiid() + wm.getlNet() + wm.getTimeOffSet() );
        Request0200 request0200;
        TelephoneCompany company = TelephoneCompany.getValue( wm.getCompany() );
        if ( company == null ) {
            List < ErrorWS > errors = new ArrayList<>();
            errors.add( new ErrorWS( "GES-01", "Invalid Mobile Company" ) );
            throw new ServerException( "Compañia Telefonica no encontrada", errors );
        }
        Tokens t = new Tokens();
        Token tp1 = new Token();
        tp1.setId( "P1" );
        tp1.add( 0, company.getCode() );
        tp1.add( 1, String.format( "%-30s", company.getCompany() ) );

        Token tqv = new Token();
        tqv.setId( "QV" );
        tqv.add( 0, String.format( "%-4s", company.getCompany() ) );
        tqv.add( 1, String.format( "%-15s", wm.getPhoneNumber() ) );
        tqv.add( 2, String.format( "%-16s", "" ) );
        tqv.add( 3, String.format( "%-12s", "" ) );
        tqv.add( 4, String.format( "%-1s", "0" ) );
        tqv.add( 5, String.format( "%-1s", "0" ) );
        tqv.add( 6, String.format( "%-4s", "" ) );
        tqv.add( 7, String.format( "%-16s", "" ) );
        tqv.add( 8, String.format( "%-2s", "11" ) );
        tqv.add( 9, String.format( "%-1s", "0" ) );
        tqv.add( 10, String.format( "%-1s", "B" ) );
        tqv.add( 11, String.format( "%-1s", "" ) );
        t.add( tp1.getId(), tp1 );
        t.add( tqv.getId(), tqv );

        if ( wm.getEmv() != null ) {
            TokenEmv tokenEmv = new TokenEmv( wm.getEmv() );
            Token tB2 = new Token();
            tB2.setId( "B2" );
            tB2.add( 0, tokenEmv.getTokenB2() );
            Token tB3 = new Token();
            tB3.setId( "B3" );
            tB3.add( 0, tokenEmv.getTokenB3() );
            Token tB4 = new Token();
            tB4.setId( "B4" );
            tB4.add( 0, tokenEmv.getTokenB4() );
            t.add( tB2.getId(), tB2 );
            t.add( tB3.getId(), tB3 );
            t.add( tB4.getId(), tB4 );

        }
        request0200 = new Request0200( dataElements, t );
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( request0200.toString() );
        }
        return request0200.getIso();
    }
}
