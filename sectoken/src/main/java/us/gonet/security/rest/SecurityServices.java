package us.gonet.security.rest;

import org.springframework.stereotype.Component;
import us.gonet.security.model.ServiceAllowed;

import java.util.LinkedHashMap;
import java.util.Map;

@Component( "securityServices" )
public class SecurityServices {

    private String jpaUrl;
    private String isoUrl;
    private String jkeUrl;
    private String rcptUrl;
    private String adpUrl;
    private String gesUrl;
    private String inqUrl;
    private String nchUrl;
    private String revUrl;
    private String srhUrl;
    private String stmtUrl;
    private String wdlUrl;
    Map< String, String > urls = new LinkedHashMap<>();
    Map< String, ServiceAllowed > serviceAlloweds = new LinkedHashMap<>();


    public String getJpaUrl() {
        return jpaUrl;
    }

    public void setJpaUrl( String jpaUrl ) {
        this.jpaUrl = jpaUrl;
        urls.put( "jdb", jpaUrl );
    }

    public String getIsoUrl() {
        return isoUrl;
    }

    public void setIsoUrl( String isoUrl ) {
        this.isoUrl = isoUrl;
        urls.put( "iso", isoUrl );
    }

    public String getJkeUrl() {
        return jkeUrl;
    }

    public void setJkeUrl( String jkeUrl ) {
        this.jkeUrl = jkeUrl;
        urls.put( "jke", jkeUrl );
    }

    public String getRcptUrl() {
        return rcptUrl;
    }

    public void setRcptUrl( String rcptUrl ) {
        this.rcptUrl = rcptUrl;
        urls.put( "rcpt", rcptUrl );
    }

    public String getAdpUrl () {
        return adpUrl;
    }

    public void setAdpUrl ( String adpUrl ) {
        this.adpUrl = adpUrl;
        urls.put( "adp", adpUrl );
    }

    public String getGesUrl () {
        return gesUrl;
    }

    public void setGesUrl ( String gesUrl ) {
        this.gesUrl = gesUrl;
        urls.put( "ges", gesUrl );
    }

    public String getInqUrl () {
        return inqUrl;
    }

    public void setInqUrl ( String inqUrl ) {
        this.inqUrl = inqUrl;
        urls.put( "inq", inqUrl );
    }

    public String getNchUrl () {
        return nchUrl;
    }

    public void setNchUrl ( String nchUrl ) {
        this.nchUrl = nchUrl;
        urls.put( "nch", nchUrl );
    }

    public String getRevUrl () {
        return revUrl;
    }

    public void setRevUrl ( String revUrl ) {
        this.revUrl = revUrl;
        urls.put( "rev", revUrl );
    }

    public String getSrhUrl () {
        return srhUrl;
    }

    public void setSrhUrl ( String srhUrl ) {
        this.srhUrl = srhUrl;
        urls.put( "srh", srhUrl );
    }

    public String getStmtUrl () {
        return stmtUrl;
    }

    public void setStmtUrl ( String stmtUrl ) {
        this.stmtUrl = stmtUrl;
        urls.put( "stmt", stmtUrl );
    }

    public String getWdlUrl () {
        return wdlUrl;
    }

    public void setWdlUrl ( String wdlUrl ) {
        this.wdlUrl = wdlUrl;
        urls.put( "wdl", wdlUrl );
    }

    public Map< String, String > getUrls () {
        return urls;
    }

    public Map< String, ServiceAllowed > getServiceAlloweds () {
        return serviceAlloweds;
    }
}
