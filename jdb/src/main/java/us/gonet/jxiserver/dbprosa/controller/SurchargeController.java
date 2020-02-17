package us.gonet.jxiserver.dbprosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.SurchargeEntity;
import us.gonet.jxiserver.dbprosa.repository.SurchargeRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.SurchargeModel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/surcharge")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class SurchargeController {

    @Autowired
    SurchargeRepository surchargeRepository;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] {"fiidAcquirer", "fiidIssuing", "id", "surcharge", "tranCode"};

    @InitBinder( "SurchargeModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @GetMapping("/{fiidAcquirer}/{fiidIssuing}/{tranCode}")
    public ResponseWrapper<SurchargeModel> getSurcharge(@PathVariable String fiidAcquirer, @PathVariable String fiidIssuing, @PathVariable String tranCode)
    {
        SurchargeEntity surchargeEntity = null;
        try {
            surchargeEntity = surchargeRepository.findByFiidAcquirerAndAndFiidIssuingAndTranCode(
                    streamFilter.sanitizeString(fiidAcquirer), streamFilter.sanitizeString(fiidIssuing), streamFilter.sanitizeString(tranCode));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        return responseFactory.buildResponseSingle(surchargeEntity, SurchargeModel.class, fiidAcquirer, fiidIssuing, tranCode);
    }

    @ResponseBody
    @GetMapping("")
    public ResponseWrapper<SurchargeModel> findAll()
    {
        List<SurchargeEntity> entities = new ArrayList<>();
        for(SurchargeEntity e : surchargeRepository.findAll())
        {
            entities.add(e);
        }
        return responseFactory.buildResponseList(entities, SurchargeModel.class, "");
    }

}
