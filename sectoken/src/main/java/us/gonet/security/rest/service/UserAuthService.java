package us.gonet.security.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import us.gonet.security.rest.IUser;
import us.gonet.security.rest.model.User;

import java.util.ArrayList;
import java.util.List;


@Component ( "userAuthService" )
public class UserAuthService implements UserDetailsService {

    private IUser repo;

    @Autowired
    public void setRepo ( IUser repo ) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername ( String id ) {
        User dev = repo.findById( id );
        if ( dev != null ) {
            return new org.springframework.security.core.userdetails.User( dev.getId(), dev.getP(), buildGrandle( dev.getQ() ) );
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