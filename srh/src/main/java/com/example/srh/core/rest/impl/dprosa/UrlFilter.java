package com.example.srh.core.rest.impl.dprosa;

import com.example.srh.exeptionsutils.ErrorWS;
import com.example.srh.exeptionsutils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import us.gonet.security.auth.AuthIn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component("rUtils")
public class UrlFilter {
    private static final String ERROR = "Error el obtener la comision";

    @Autowired
    private AuthIn authIn;

    public RequestEntity getRequestEntity(URI url) throws ServerException {

        if(urlWhiteList(url))
        {
            return RequestEntity
                    .get(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", authIn.getTokenJDBServices())
                    .build();
        }
        else
        {
            List<ErrorWS> errs = new ArrayList<>();
            errs.add(new ErrorWS("SRH10", "Invalid URL"));
            throw new ServerException(ERROR, errs);
        }
    }

    private boolean urlWhiteList(URI url)
    {
        return url.getScheme().startsWith("http");
    }
}
