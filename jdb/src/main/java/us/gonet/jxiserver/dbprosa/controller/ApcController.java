package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.config.Utils;
import us.gonet.jxiserver.dbprosa.entity.ApcEntity;
import us.gonet.jxiserver.dbprosa.repository.ApcRepository;
import us.gonet.jxiserver.dbprosa.response.ErrorWS;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Apc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/apc")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
//@API
public class ApcController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ApcRepository apcRepository;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] { "key", "idf", "sharingGroup", "allowedCode", "rountingGroup" };

    @InitBinder( "Apc" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @GetMapping()
    public ResponseEntity findAllApc()
    {
        List<Apc> mode = modelMapper.map(apcRepository.findAll(), List.class);
        return ResponseEntity.ok(mode);
    }

    @ResponseBody
    @PostMapping("/batch")
    public ResponseEntity saveBatchAtd(@RequestBody List<Apc> apcs)
    {
        List<ApcEntity> entities = new ArrayList<>();
        for(Apc apc : apcs) {
            entities.add(modelMapper.map(apc, ApcEntity.class));
        }
        List<Apc> response = new ArrayList<>();
        for(ApcEntity apcEntity : apcRepository.saveAll(entities)) {
            response.add(modelMapper.map(apcEntity, Apc.class));
        }
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping()
    public ResponseEntity saveApc(@RequestBody  Apc apc)
    {
        ApcEntity entity = modelMapper.map(apc, ApcEntity.class);
        Apc apcResponse = modelMapper.map(apcRepository.save(entity),Apc.class );
        return ResponseEntity.ok(apcResponse);
    }

    @ResponseBody
    @GetMapping("/{key}/and/{fiid}")
    public ResponseWrapper<Apc> findByKeyAndFiid(@PathVariable String key, @PathVariable String fiid)
    {
        try {
            validateEntry(streamFilter.sanitizeString(key), streamFilter.sanitizeString(fiid), true);
            ApcEntity entitiy = apcRepository.findByKeyAndIdfFiid(streamFilter.sanitizeString(key),
                    streamFilter.sanitizeString(fiid));
            return responseFindOne(entitiy, key, fiid);
        } catch (ServerException e) {
            ResponseWrapper<Apc> respose = new ResponseWrapper<>();
            respose.addError(new ErrorWS("JDB020", "Parametro de entrada Invalido" ));
            respose.setCode("01");
            return respose;
        }

    }

    @ResponseBody
    @GetMapping("/{key}")
    public ResponseWrapper<Apc> findByKey(@PathVariable String key)
    {
        try {
            validateEntry(streamFilter.sanitizeString(key), "", false);
            ApcEntity entity = apcRepository.findByKey(streamFilter.sanitizeString(key));
            return responseFindOne(entity, key);
        } catch (ServerException e) {
            ResponseWrapper<Apc> respose = new ResponseWrapper<>();
            respose.addError(new ErrorWS("JDB020", "Parametro de entrada Invalido" ));
            respose.setCode("01");
            return respose;
        }
    }

    private ResponseWrapper<Apc> responseFindOne(ApcEntity entity, String... params)
    {
        ResponseWrapper<Apc> respose = new ResponseWrapper<>();

        if(entity == null)
        {
            respose.addError(new ErrorWS("JDB001", "Registro no encontrado: "+ Arrays.toString(params) ));
            respose.setCode("01");
        }
        else
        {
            respose.setCode("00");
            respose.addBody(modelMapper.map(entity, Apc.class));
        }
        return respose;
    }

    private void validateEntry(String key, String fiid, boolean fiidNeed) throws ServerException {
        if(Utils.stringIsBlank(key) || key.length() > 8)
        {
            throw new ServerException("Parametro de entrada invalido", null);
        }
        if(fiidNeed && (Utils.stringIsBlank(fiid) || fiid.length() != 4))
        {
            throw new ServerException("Parametro de entrada invalido", null);
        }
    }




}
