package us.gonet.security.auth;

import com.google.gson.Gson;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import us.gonet.security.model.KeyAuth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import static java.util.Collections.emptyList;

class JwtUtil {
    @Value ( "${security.jwt.token.secret-KeyAuth:secret}" )


    private static final Logger LOG = LoggerFactory.getLogger( JwtUtil.class );
    private static final long VALIDITY_IN_MILLISECONDS = 1000L * 60L * 60L * 24L * 10L;

    private JwtUtil () {
    }

    static void addAuthentication ( HttpServletResponse res, String device, String key ) throws IOException {
        Gson gson = new Gson();
        Date now = new Date();
        Date timeOut = new Date( now.getTime() + VALIDITY_IN_MILLISECONDS );
        String token = Jwts.builder().setSubject( device )
                .setExpiration( timeOut )
                .signWith( SignatureAlgorithm.HS512, key ).compact();
        res.setCharacterEncoding( "UTF-8" );
        res.addHeader("Content-Type", "application/json");
        KeyAuth k = new KeyAuth();
        k.setCode( "200" );
        k.setMessage( "authorization" );
        k.setAccessToken( token );
        k.setExpiresIn( "20 days" );
        k.setScope( "Read,Write" );
        String responseComplete = gson.toJson( k );
        PrintWriter out = res.getWriter();
        out.print( responseComplete );
        out.flush();
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( String.format( "Successful key request: %s", token ) );
        }
    }

    static Authentication getAuthentication ( HttpServletRequest request, String key, String localUser ) {
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

                if ( ! user.equals( localUser ) ) {
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
