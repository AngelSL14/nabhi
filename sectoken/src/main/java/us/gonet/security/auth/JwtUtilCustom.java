package us.gonet.security.auth;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtUtilCustom {
    Authentication getAuthentication (HttpServletRequest request, String key, String localUser );
}
