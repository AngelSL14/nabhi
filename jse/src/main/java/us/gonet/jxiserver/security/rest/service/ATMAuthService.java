package us.gonet.jxiserver.security.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import us.gonet.jxiserver.i8583.rest.IRest;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.ATD;

import java.util.ArrayList;
import java.util.List;

@Service ( "atmAuthService" )
public class ATMAuthService implements UserDetailsService {

    @Autowired
    @Qualifier ( "atdRest" )
    private IRest repo;

    @Override
    public UserDetails loadUserByUsername ( String ip ) {
        ATD dev;
        try {
            dev = repo.getATD( ip );
        } catch ( ServerException e ) {

            throw new UsernameNotFoundException( "NOT USER FOUNDED" );
        }
        if ( dev != null ) {
            return new org.springframework.security.core.userdetails.User( dev.getAtm().getId(), dev.getAtm().getId(), buildGrandle( ( byte ) 1 ) );
        } else {
            throw new UsernameNotFoundException( "NOT USER FOUNDED" );
        }
    }

    private List< GrantedAuthority > buildGrandle ( byte rol ) {
        String[] rolesEntity = { "LECTOR", "USUARIO", "ADMINISTRADOR" };
        List< GrantedAuthority > authorities = new ArrayList<>();
        for ( int i = 0; i < rol; i++ ) {
            authorities.add( new SimpleGrantedAuthority( rolesEntity[ i ] ) );
        }
        return authorities;
    }
}