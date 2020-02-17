package us.gonet.jxiserver.dbprosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.business.ICountyBusiness;
import us.gonet.jxiserver.dbprosa.entity.CountyEntity;
import us.gonet.jxiserver.dbprosa.entity.lite.CountyEntityLite;
import us.gonet.jxiserver.dbprosa.repository.CountyRepository;
import us.gonet.jxiserver.dbprosa.repository.lite.CountyRepositoryLite;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.serverutils.model.jdb.County;
import us.gonet.serverutils.model.jdb.lite.CountyLite;

import java.util.List;

@RestController
@RequestMapping("county")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class CountyController {

    @Autowired
    ICountyBusiness countyBusinessImpl;

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    CountyRepositoryLite countyRepositoryLite;

    @Autowired
    ResponseFactory responseFactory;

    private static final String[] ALLOWED_FIELDS = new String[] {"countyCodeId", "countyCode", "countyName", "state"};

    @InitBinder( "County" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/batch")
    public ResponseEntity saveBatch(@RequestBody List<County> buttons)
    {
        return ResponseEntity.ok(countyBusinessImpl.saveBatch(buttons));
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper<County> findAll()
    {
        List<CountyEntity> entities = countyRepository.findAll();
        return responseFactory.buildResponseList(entities, County.class, "");
    }

    @ResponseBody
    @GetMapping("/lite")
    public ResponseWrapper<CountyLite> findAllLite()
    {
        List<CountyEntityLite> entities = countyRepositoryLite.findAll();
        return responseFactory.buildResponseList(entities, CountyLite.class, "");
    }
}

