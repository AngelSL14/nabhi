package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.RcptEntity;
import us.gonet.jxiserver.dbprosa.repository.RcptRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.njdb.Rcpt;
import java.util.Optional;

@RestController
@RequestMapping( "rcptBd" )
@CrossOrigin( origins = { "*" }, maxAge = 6000 )
public class RcptController {

    @Autowired
    RcptRepository rcptRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;
    @Autowired
    StreamFilter streamFilter;

    @InitBinder( "rcpt" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields(  "header", "body", "trailer", "receipt" );
    }


    @ResponseBody
    @PutMapping( "/save" )
    public ResponseWrapper updateRcpt( @RequestBody Rcpt rcpt ) {
        RcptEntity entity = modelMapper.map( rcpt, RcptEntity.class );
        RcptEntity update = rcptRepository.save( entity );
        return responseFactory.buildResponseSingle( update, Rcpt.class, ( String ) null );
    }

    @ResponseBody
    @GetMapping( "/{key:.+}" )
    public ResponseWrapper<Rcpt> findRcptByKey( @PathVariable String key ) {
        ResponseWrapper<Rcpt> response;
        Optional<RcptEntity> entity = null;
        try {
            entity = rcptRepository.findById( streamFilter.sanitizeString(key) );
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        if(entity.isPresent())
        {
            response = responseFactory.buildResponseSingle( entity.get(), Rcpt.class, key );
        }
        else
        {
            response = responseFactory.buildResponseSingle( null, Rcpt.class, key );
        }
        return response;
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper findAllRcpt() {
        return responseFactory.buildResponseList( rcptRepository.findAll(), Rcpt.class, null);
    }

}
