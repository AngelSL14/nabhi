package us.gonet.jxiserver.dbprosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.BinEntity;
import us.gonet.jxiserver.dbprosa.repository.BinRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.BinModel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bin")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class BinController {
    @Autowired
    BinRepository binRepository;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] { "bin", "id", "idf"};

    @InitBinder( "BinModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @GetMapping("/{bin}")
    public ResponseWrapper<BinModel> getBin(@PathVariable String bin)
    {
        BinEntity entity = null;
        try {
            entity = binRepository.findByBin(streamFilter.sanitizeString(bin));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        return responseFactory.buildResponseSingle(entity, BinModel.class, bin);
    }

    @ResponseBody
    @GetMapping("/like/{bin}")
    public ResponseWrapper<BinModel> getBinLike(@PathVariable String bin)
    {
        BinEntity entity = null;
        try {
            entity = binRepository.findByBinStartingWith(streamFilter.sanitizeString(bin));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        return responseFactory.buildResponseSingle(entity, BinModel.class, bin);
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper<BinModel> findAll()
    {
        List<BinEntity> entity = new ArrayList<>();
        for(BinEntity b : binRepository.findAll())
        {
            entity.add(b);
        }
        return responseFactory.buildResponseList(entity, BinModel.class, null);
    }
}
