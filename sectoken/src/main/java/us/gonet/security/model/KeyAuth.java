package us.gonet.security.model;

public class KeyAuth {

    private String code;
    private String message;
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String expiresIn;
    private String scope;

    public String getCode () {
        return code;
    }

    public void setCode ( String code ) {
        this.code = code;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    public String getAccessToken () {
        return accessToken;
    }

    public void setAccessToken ( String accessToken ) {
        this.accessToken = accessToken;
    }

    public String getTokenType () {
        return tokenType;
    }

    public void setTokenType ( String tokenType ) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken () {
        return refreshToken;
    }

    public void setRefreshToken ( String refreshToken ) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn () {
        return expiresIn;
    }

    public void setExpiresIn ( String expiresIn ) {
        this.expiresIn = expiresIn;
    }

    public String getScope () {
        return scope;
    }

    public void setScope ( String scope ) {
        this.scope = scope;
    }

    @Override
    public String toString () {
        return "KeyAuth{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
