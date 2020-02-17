package us.gonet.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component( "jwlFilter" )
public class JwtFilter extends GenericFilterBean {

    private String key;
    private String localUser;

    private JwtUtilCustom custom;


    @Override
    public void doFilter ( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
        Authentication auth;
        if(custom == null)
        {
            auth = JwtUtil.getAuthentication( ( HttpServletRequest ) request, key, localUser );
        }
        else
        {
            auth = custom.getAuthentication(( HttpServletRequest ) request, key, localUser);
        }
        SecurityContextHolder.getContext().setAuthentication( auth );
        chain.doFilter( request, response );
    }

    public void setKey ( String key ) {
        this.key = key;
    }

    public void setLocalUser ( String localUser ) {
        this.localUser = localUser;
    }

    public void setCustom(JwtUtilCustom custom) {
        this.custom = custom;
    }
}
