package us.gonet.jxiserver.i8583.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.Apc;
import us.gonet.serverutils.model.jdb.SurchargeModel;
import us.gonet.serverutils.model.srh.RequestSurchage;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Component("isoUtils")
public class ISOUtils {

    private static final String ZONE_HOST = "America/Mexico_City";

    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    AuthIn authIn;

    private ISOUtils() {
    }

    public String allowGroup( ATD atd, Apc apc ) {
        return apc.getSharingGroup() + apc.getAllowedCode().getAllowedCode() + atd.getCounty().getState().getStateCode() + atd.getCounty().getCountyCode() + atd.getIdf().getCountry().getCountryCode() + apc.getRountingGroup();
    }

    public String surchargeValue( String ip, String track, String tranCode ) throws ServerException {
        RequestSurchage requestSurchage = new RequestSurchage();
        requestSurchage.setIp(ip);
        requestSurchage.setTrack(track);
        requestSurchage.setTransactionCode(tranCode);
        ResponseWrapper<SurchargeModel> surchargeREsp = restRequestFactory.buildRestRequest("/surchage/sendSurchage",
                SurchargeModel.class, "srh", "No se pudo obtener la comision",
                "post", requestSurchage, null);

        return surchargeREsp.getBody().get(0).getSurcharge();
    }

    public String obfuscateCardNumber(String cardNumber)
    {
        StringBuilder obs = new StringBuilder();
        int i = 0;
        for(char c : cardNumber.toCharArray())
        {
            if(i < cardNumber.length() - 4)
            {
                obs.append("*");
            }
            else
            {
                obs.append(c);
            }
            i++;
        }
        return obs.toString();
    }

    public String verifyUTCDifference ( String zone ) {
        ZoneId center = ZoneId.of( ZONE_HOST );
        ZonedDateTime zoneHost = Instant.now().atZone( center );
        int secondsHost = zoneHost.getOffset().getTotalSeconds();
        ZoneId atm = ZoneId.of( zone );
        ZonedDateTime atmZone = Instant.now().atZone( atm );
        int secondsATM = atmZone.getOffset().getTotalSeconds();
        if ( secondsHost == secondsATM ){
            return "+000";
        }
        if ( secondsHost > secondsATM ) {
            String dif = String.valueOf( ( secondsHost - secondsATM ) / 60 );
            if ( dif.length() < 3 ) {
                dif = "0" + dif;
            }
            return "-" + dif;
        } else {
            String dif = String.valueOf( ( secondsATM - secondsHost ) / 60 );
            if ( dif.length() < 3 ) {
                dif = "0" + dif;
            }
            return "+" + dif;
        }
    }
}
