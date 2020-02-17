package us.gonet.jxiserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.SecurityServices;

@Component( "configProps" )
public class ConfigProperties {

    @Autowired
    PropertiesServices propertiesServices;

    @Autowired
    SecurityServices securityServices;

    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private JwtFilter jwtFilter;


    private static final Logger LOG = LoggerFactory.getLogger( ConfigProperties.class );

    public void config(){
        WdlProperties wdlProperties = ( WdlProperties ) propertiesServices.getProperties().get( "wdl" );
        InqProperties inqProperties = ( InqProperties ) propertiesServices.getProperties().get( "inq" );
        GesProperties gesProperties = ( GesProperties ) propertiesServices.getProperties().get( "ges" );
        NchProperties nchProperties = ( NchProperties ) propertiesServices.getProperties().get( "nch" );
        StmtProperties stmtProperties = ( StmtProperties ) propertiesServices.getProperties().get( "stmt" );
        JdbProperties jdbProperties = ( JdbProperties ) propertiesServices.getProperties().get( "jdb" );
        AdpProperties adpProperties = ( AdpProperties ) propertiesServices.getProperties().get( "adp" );
        JkeProperties jkeProperties = ( JkeProperties ) propertiesServices.getProperties().get( "jke" );
        IsoProperties isoProperties = ( IsoProperties ) propertiesServices.getProperties().get( "iso" );
        SrhProperties srhProperties = ( SrhProperties ) propertiesServices.getProperties().get( "srh" );
        ReversalProperties reversalProperties = ( ReversalProperties ) propertiesServices.getProperties().get( "reversal" );
        Properties props = (Properties) propertiesServices.getProperties().get("props");
        String wdl = wdlProperties.getProtocol() + wdlProperties.getHost() +  ":" + wdlProperties.getPort() + wdlProperties.getPath();
        String inq = inqProperties.getProtocol() + inqProperties.getHost() +  ":" + inqProperties.getPort() + inqProperties.getPath();
        String ges = gesProperties.getProtocol() + gesProperties.getHost() +  ":" + gesProperties.getPort() + gesProperties.getPath();
        String nch = nchProperties.getProtocol() + nchProperties.getHost() +  ":" + nchProperties.getPort() + nchProperties.getPath();
        String stmt = stmtProperties.getProtocol() + stmtProperties.getHost() +  ":" + stmtProperties.getPort() + stmtProperties.getPath();
        String jdb = jdbProperties.getProtocol() + jdbProperties.getHost() +  ":" + jdbProperties.getPort() + jdbProperties.getPath();
        String adp = adpProperties.getProtocol() + adpProperties.getHost() +  ":" + adpProperties.getPort() + adpProperties.getPath();
        String jke = jkeProperties.getProtocol() + jkeProperties.getHost() +  ":" + jkeProperties.getPort() + jkeProperties.getPath();
        String iso = isoProperties.getProtocol() + isoProperties.getHost() +  ":" + isoProperties.getPort() + isoProperties.getPath();
        String srh = srhProperties.getProtocol() + srhProperties.getHost() +  ":" + srhProperties.getPort() + srhProperties.getPath();
        String rev = reversalProperties.getProtocol() + reversalProperties.getHost() +  ":" + reversalProperties.getPort() + reversalProperties.getPath();

        securityServices.setWdlUrl( wdl );
        securityServices.setInqUrl( inq );
        securityServices.setGesUrl( ges );
        securityServices.setNchUrl( nch );
        securityServices.setStmtUrl( stmt );
        securityServices.setJpaUrl( jdb );
        securityServices.setAdpUrl( adp );
        securityServices.setJkeUrl ( jke );
        securityServices.setIsoUrl( iso );
        securityServices.setSrhUrl( srh );
        securityServices.setRevUrl( rev );

        loginFilter.setKey( props.getKey() );
        jwtFilter.setKey( props.getKey() );
        jwtFilter.setLocalUser( props.getLocalUserTkn() );

        if ( LOG.isInfoEnabled() ){
            LOG.info( "URLs configured successfully" );
        }

    }

}
