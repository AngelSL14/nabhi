package us.gonet.jxiserver.security.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import us.gonet.security.auth.JwtUtilCustom;
import us.gonet.serverutils.model.jdb.Atm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Component( "jwFilt" )
public class JwtUt implements JwtUtilCustom {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUt.class);
    private List<Atm> atmList;

    public void setAtmList(List<Atm> atmList) {
        this.atmList = atmList;
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request, String key, String localUser) {
        String token = request.getHeader( "Authorization" );
        if ( token != null ) {
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "Incoming key request" );
                LOG.debug( "Verified key" );
            }
            try {
                String user = Jwts.parser().setSigningKey( key )
                        .parseClaimsJws( token )
                        .getBody().getSubject();
                List<Atm> list = atmList.stream().filter(atm ->
                        atm.getId().equals(user)).collect(Collectors.toList());
                if (list.isEmpty() || !user.equals(list.get(0).getId())) {
                    LOG.error( "Invalid Token for this service" );
                    return null;
                } else {
                    if ( LOG.isDebugEnabled() ) {
                        LOG.debug( "Verified key successfully" );
                    }
                }
                return new UsernamePasswordAuthenticationToken( user, null, emptyList() );
            } catch ( MalformedJwtException | ExpiredJwtException e ) {
                LOG.error( "Token expired" );
            } catch ( SignatureException e ) {
                LOG.error( "Invalid Token" );
            }
        }
        LOG.error( "Missing Token Data" );
        return null;
    }

}

