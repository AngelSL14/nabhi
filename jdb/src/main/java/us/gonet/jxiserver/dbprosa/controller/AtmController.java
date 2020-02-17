package us.gonet.jxiserver.dbprosa.controller;

import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.AtmEntity;
import us.gonet.jxiserver.dbprosa.repository.AtmRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Atm;

import java.util.List;

@RestController
@RequestMapping("atm")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class AtmController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AtmRepository atmRepository;
    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;


    private static final String[] ALLOWED_FIELDS = new String[] { "id", "ip", "lastTrx",
            "activeTrx", "sucursal", "receipt" };

    @InitBinder( "Atm" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @GetMapping()
    @ApiOperation(value="Devuelve todos los cajeros registrados")
    public ResponseWrapper<Atm> findAllAtms()
    {
        List<AtmEntity> entities = atmRepository.findAll();
        return responseFactory.buildResponseList(entities, Atm.class, null);
    }

    @ResponseBody
    @GetMapping("/{ip:.+}")
    @ApiOperation(value="Devuelve el cajero identificado con la ip solicitada")

    public ResponseWrapper<Atm> findAtmByIp(@PathVariable String ip)
    {
        AtmEntity entity = null;
        try {
            entity = atmRepository.findByIp(streamFilter.sanitizeString(ip));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        return responseFactory.buildResponseSingle(entity, Atm.class, ip);
    }

    @ResponseBody
    @PostMapping("")
    @ApiOperation(value="Da de alta un nuevo cajero o actualiza un cajero registrado",
    notes = "Para dar de alta se debe omitir el 'id', para actualizar hay que incluir el 'id'")
    public ResponseWrapper<Atm> updateAtm(@RequestBody Atm atm)
    {
        AtmEntity entity = modelMapper.map(atm, AtmEntity.class);
        AtmEntity update = atmRepository.save(entity);
        return responseFactory.buildResponseSingle(update, Atm.class, null);
    }
}
