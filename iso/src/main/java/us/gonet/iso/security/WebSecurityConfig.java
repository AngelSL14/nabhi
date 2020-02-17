package us.gonet.iso.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.service.UserAuthService;


//@Configuration
@ComponentScan ( "us.gonet.security" )
@EnableWebSecurity
@EnableGlobalMethodSecurity ( prePostEnabled = true )

//*@CrossOrigin(origins = "*",maxAge = 6000)
@CrossOrigin ( origins = "10.255.11.148", allowedHeaders = "*", maxAge = 6000 )
@Import( { LoginFilter.class , JwtFilter.class } )

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserAuthService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private JwtFilter jwtFilter;

    private static final String URL = "/auth";

    @Override
    protected void configure ( AuthenticationManagerBuilder m ) throws Exception {
        m.userDetailsService( userService ).passwordEncoder( bCryptPasswordEncoder );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter () {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials( true );
        config.addAllowedOrigin( "*" );
        config.addAllowedHeader( "*" );
        config.addExposedHeader( HttpHeaders.AUTHORIZATION );
        config.addAllowedMethod( "*" );
        source.registerCorsConfiguration( URL + "**", config );
        return new CorsFilter( source );
    }

    @Override
    public void configure( WebSecurity web ) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }


    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        loginFilter.init( URL, authenticationManager() );
        http.cors();
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin( "*" );
        config.addAllowedHeader( "*" );
        config.addAllowedMethod( "GET" );
        config.addAllowedMethod( "PUT" );
        config.addAllowedMethod( "POST" );
        http.cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers( HttpMethod.POST, URL ).permitAll()//Permitimos acceso al login a cualquier
                //.antMatchers(HttpMethod.POST,"/iso/process").permitAll()
                .anyRequest().authenticated() //cualquier otra peticion requiere autentificacion
                //.antMatchers().authenticated()
                .and()
                .cors()
                .and()
                //Las peticiones /login pasaran previamente por este fltro
                .addFilterBefore( loginFilter ,
                        UsernamePasswordAuthenticationFilter.class
                )
                //Las demas peticiones pasaran por este filtro para validar el token
                .addFilterBefore( jwtFilter ,
                        UsernamePasswordAuthenticationFilter.class
                ).cors().configurationSource( request -> new CorsConfiguration().applyPermitDefaultValues() );
    }
}
