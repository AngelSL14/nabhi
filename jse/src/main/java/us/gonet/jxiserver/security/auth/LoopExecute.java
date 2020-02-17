package us.gonet.jxiserver.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.config.*;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.security.auth.AuthIn;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.model.ServiceAllowed;
import us.gonet.security.rest.model.User;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Atm;

import java.util.Timer;
import java.util.TimerTask;

@Configuration()
@Import( { AuthIn.class } )
@Component
public class LoopExecute {

    private static final Logger LOG = LoggerFactory.getLogger(LoopExecute.class);


    @Autowired
    AuthIn authIn;

    @Autowired
    PropertiesServices propertiesServices;

    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired @Qualifier("jwFilt")
    JwtUt jwtUt;

    @Autowired
    private JwtFilter jwtFilter;

    public void refrestToken(){
        User uwdl = new User();
        WdlProperties wdlProperties = ( WdlProperties ) propertiesServices.getProperties().get( "wdl" );
        uwdl.setId( wdlProperties.getUser() );
        uwdl.setP( wdlProperties.getPwd() );

        User uinq = new User();
        InqProperties inqProperties = ( InqProperties ) propertiesServices.getProperties().get( "inq" );
        uinq.setId( inqProperties.getUser() );
        uinq.setP( inqProperties.getPwd() );

        User uges = new User();
        GesProperties gesProperties = ( GesProperties ) propertiesServices.getProperties().get( "ges" );
        uges.setId( gesProperties.getUser() );
        uges.setP( gesProperties.getPwd() );

        User unch = new User();
        NchProperties nchProperties = ( NchProperties ) propertiesServices.getProperties().get( "nch" );
        unch.setId( nchProperties.getUser() );
        unch.setP( nchProperties.getPwd() );

        User ustmt = new User();
        StmtProperties stmtProperties = ( StmtProperties ) propertiesServices.getProperties().get( "stmt" );
        ustmt.setId( stmtProperties.getUser() );
        ustmt.setP( stmtProperties.getPwd() );

        User ujdb = new User();
        JdbProperties jdbProperties = ( JdbProperties ) propertiesServices.getProperties().get( "jdb" );
        ujdb.setId( jdbProperties.getUser() );
        ujdb.setP( jdbProperties.getPwd() );

        User uadp = new User();
        AdpProperties adpProperties = ( AdpProperties ) propertiesServices.getProperties().get( "adp" );
        uadp.setId( adpProperties.getUser() );
        uadp.setP( adpProperties.getPwd() );

        User ujke = new User();
        JkeProperties jkeProperties = ( JkeProperties ) propertiesServices.getProperties().get( "jke" );
        ujke.setId( jkeProperties.getUser() );
        ujke.setP( jkeProperties.getPwd() );

        User usrh = new User();
        SrhProperties srhProperties = ( SrhProperties ) propertiesServices.getProperties().get( "srh" );
        usrh.setId( srhProperties.getUser() );
        usrh.setP( srhProperties.getPwd() );

        User urev = new User();
        ReversalProperties reversalProperties = ( ReversalProperties ) propertiesServices.getProperties().get( "reversal" );
        urev.setId( reversalProperties.getUser() );
        urev.setP( reversalProperties.getPwd() );

        Timer timer = new Timer("timer");
        TimerTask repeadTask = new TimerTask() {
            @Override
            public void run() {
                authIn.startAuthServices(
                        new ServiceAllowed( uwdl, "WDL", false ),
                        new ServiceAllowed( uinq, "INQ", false ),
                        new ServiceAllowed( uges, "GES", false ),
                        new ServiceAllowed( unch, "NCH", false ),
                        new ServiceAllowed( ustmt, "STMT", false ),
                        new ServiceAllowed( ujdb, "JDB", true ),
                        new ServiceAllowed( uadp, "ADP", false ),
                        new ServiceAllowed( ujke, "JKE", false ),
                        new ServiceAllowed( usrh, "SRH", false ),
                        new ServiceAllowed( urev, "REV", false )
                );

                try {
                    ResponseWrapper<Atm> atmResponse = restRequestFactory.buildRestRequest("/atm", Atm.class, "jdb",
                            "No se encontro el cajero", "get", "",null);
                    jwtUt.setAtmList(atmResponse.getBody());
                    jwtFilter.setCustom(jwtUt);
                } catch (ServerException e) {
                    String msg = "Hubo un error al obtener la lista de ATM"+e.getErrors().toString();
                    LOG.error(msg);
                }
            }
        };
        long delay = 1000L;
        long periond = 1000L * 60L * 60L * 24 * 2L;
        timer.scheduleAtFixedRate(repeadTask,delay,periond);



    }
}
