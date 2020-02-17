package us.gonet.jxiserver.dbprosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.business.IIdfBusiness;
import us.gonet.jxiserver.dbprosa.entity.IDFEntity;
import us.gonet.jxiserver.dbprosa.repository.IDFRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.IDF;

import java.util.List;

@RestController
@RequestMapping("idf")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class IdfController {

    @Autowired
    IIdfBusiness idfBusinessImpl;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    IDFRepository idfRepository;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] {"fiid", "logicalNet", "name", "acquiringId",
        "nameLong", "country"};

    @InitBinder( "IDF" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/batch")
    public ResponseEntity saveBatch(@RequestBody List<IDF> countries)
    {
        return ResponseEntity.ok(idfBusinessImpl.saveBatch(countries));
    }

    @ResponseBody
    @GetMapping("/{fiid}")
    public ResponseWrapper<IDF> findByFiid(@PathVariable String fiid)
    {
        IDFEntity entity;
        try {
            entity = idfRepository.findByFiid(streamFilter.sanitizeString(fiid));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        return responseFactory.buildResponseSingle(entity, IDF.class, fiid);
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper<IDF> findAll()
    {
        List<IDFEntity> entities = idfRepository.findAll();
        return responseFactory.buildResponseList(entities, IDF.class, "");
    }
}
