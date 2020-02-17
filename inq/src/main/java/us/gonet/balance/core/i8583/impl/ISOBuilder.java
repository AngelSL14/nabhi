package us.gonet.balance.core.i8583.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import us.gonet.balance.core.i8583.IISOBuilder;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.iso8583.message.Request0200;
import us.gonet.r8583.rest.IRest;
import us.gonet.r8583.rest.impl.jke.TranslatePinBlock;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.token.Token;
import us.gonet.token.Tokens;
import us.gonet.token.emv.TokenEmv;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Import ( { TranslatePinBlock.class} )
@Component ( "iSOBuilder" )
public class ISOBuilder implements IISOBuilder {

    private static final Logger LOG = LoggerFactory.getLogger( ISOBuilder.class );

    @Autowired
    @Qualifier( "translatePinBlock" )
    IRest translatePinBlock;

    @Override
    public ISO buildMessage ( ATMRequestModel wm ) throws ServerException {
        try{
            wm.setPinBlock( translatePinBlock.translatePinBlock( wm.getPinBlock(), wm.getTrack2(), wm.getTermId(), wm.getTermType(), wm.getNodeSingle()  ) );
        }catch ( ServerException e ){
            throw new ServerException( "Error al construir el mensaje ISO-8583", e.getErrors() );
        }
        Map < String, String > dataElements = new LinkedHashMap <>();
        dataElements.put( "p3", TranCodes.BALANCE_INQUIRY.getValue() + wm.getFromAccount() + wm.getToAccount() );
        DecimalFormat f = new DecimalFormat("#,###,##0.00" );
        float amount = Float.parseFloat ( wm.getAmount() );
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
            Tokens t = new Tokens();
            t.add( tB2.getId(), tB2 );
            t.add( tB3.getId(), tB3 );
            t.add( tB4.getId(), tB4 );
            request0200 = new Request0200( dataElements, t );
        } else {
            request0200 = new Request0200( dataElements );
        }
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( request0200.toString() );
        }
        return request0200.getIso();
    }
}
