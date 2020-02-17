package com.example.srh.exeptionsutils;

import java.util.List;

public class ServerException extends Exception {

    private final String errorMessage;
    private final transient List < ErrorWS > errors;

    public ServerException ( String errorMessage, List < ErrorWS > errors ) {
        super();
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    @Override
    public String getMessage () {
        return errorMessage;
    }

    public List < ErrorWS > getErrors () {
        return errors;
    }
}
