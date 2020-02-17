package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.config.Utils;
import us.gonet.jxiserver.dbprosa.entity.ATDEntity;
import us.gonet.jxiserver.dbprosa.entity.lite.ATDEntityLite;
import us.gonet.jxiserver.dbprosa.repository.ATDRepository;
import us.gonet.jxiserver.dbprosa.repository.AtmRepository;
import us.gonet.jxiserver.dbprosa.repository.lite.ATDRepositoryLite;
import us.gonet.jxiserver.dbprosa.response.ErrorWS;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.lite.ATDLite;

import java.util.List;

@RestController
@RequestMapping("/atd")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class AtdController {

    @Autowired
    ATDRepository atdRepository;

    @Autowired
    ATDRepositoryLite atdRepositoryLite;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AtmRepository atmRepository;
    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] { "terminalId", "atm", "sequenceNumber",
            "deviceType", "idf", "county", "nodeProsa" };

    @InitBinder( "ATD" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @GetMapping()
    public ResponseWrapper<ATD> findAllAtd()
    {
        List<ATDEntity> entities = atdRepository.findAll();

        return responseFactory.buildResponseList(entities, ATD.class, "");
    }

    @ResponseBody
    @GetMapping("/lite")
    public ResponseWrapper<ATDLite> findAllAtdLite()
    {
        List<ATDEntityLite> entities = atdRepositoryLite.findAll();
        return responseFactory.buildResponseList(entities, ATDLite.class, "");
    }

    @ResponseBody
    @PostMapping("/lite")
    public ResponseWrapper<ATDLite> saveAtdLite(@RequestBody ATDLite atd)
    {
        ATDEntityLite entity = atdRepositoryLite.save(modelMapper.map(atd, ATDEntityLite.class));
        return responseFactory.buildResponseSingle(entity, ATDLite.class, atd.toString());
    }

    @ResponseBody
    @PostMapping("/batch")
    public ResponseEntity saveBatchAtd(@RequestBody List<ATD> atd)
    {
        List<ATDEntity> entity = modelMapper.map(atd, List.class);
        return ResponseEntity.ok(atdRepository.saveAll(entity));
    }

    @ResponseBody
    @PostMapping()
    public ResponseWrapper<ATD> saveAtd(@RequestBody ATD atd)
    {
        ATDEntity entity = modelMapper.map(atd, ATDEntity.class);

        return responseFactory.buildResponseSingle(entity, ATD.class, atd.toString());
    }

    @ResponseBody
    @GetMapping("{ip:.+}")
    public ResponseWrapper<ATD> findByIp(@PathVariable String ip)
    {
        ResponseWrapper<ATD> respose = new ResponseWrapper<>();
        String validIp = Utils.validateIp1(ip);
        if(validIp == null)
        {
            respose = new ResponseWrapper();
            respose.setCode("01");
            respose.addError(new ErrorWS("JDB020", "Parametro de entrada Invalido" ));
            return respose;
        }

        try {
            ATDEntity entity = atdRepository.findAtdByIp(streamFilter.sanitizeString(validIp));
            if(entity == null)
            {
                respose.addError(new ErrorWS("JDB001", "Registro no encontrado: "+validIp));
                respose.setCode("01");
            }
            else
            {
                respose.setCode("00");
                respose.addBody(modelMapper.map(entity, ATD.class));
            }
        } catch (ServerException e) {

            return streamFilter.sanitizeError();
        }

        return respose;
    }

    @ResponseBody
    @GetMapping("/sequence/{atmIp:.+}")
    public ResponseWrapper<ATD> getSequenceNumber(@PathVariable String atmIp)
    {
        ResponseWrapper<ATD> response;
        List<ErrorWS> errors = Utils.validateIp(atmIp);
        if(!errors.isEmpty())
        {
            response = new ResponseWrapper();
            response.addAllError(errors);
            response.setCode("01");
            return response;
        }
        ATDEntity entity = null;
        try {
            entity = atdRepository.findAtdByIp(streamFilter.sanitizeString(atmIp));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        response = responseFactory.buildResponseSingle(entity, ATD.class, atmIp);
        int newSequenceNumber = Integer.parseInt(entity.getSequenceNumber()) + 1;
        entity.setSequenceNumber(String.valueOf(newSequenceNumber));
        atdRepository.save(entity);
        return response;
    }




}
