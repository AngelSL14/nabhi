package us.gonet.security.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Crypto {
    /***
     * Método utilizado para obtener el hashcode de una cadena
     * **/
    private Crypto () {
    }

    public static String getEncryption ( String str ) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode( str );
    }
}
