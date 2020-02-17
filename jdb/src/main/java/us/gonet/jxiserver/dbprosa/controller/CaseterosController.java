package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.business.ICaseterosBusiness;
import us.gonet.jxiserver.dbprosa.entity.CaseterosEntity;
import us.gonet.jxiserver.dbprosa.repository.CaseteroRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("caseteros")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class CaseterosController {

    @Autowired @Qualifier("caseterosBus")
    ICaseterosBusiness caseterosBusiness;

    @Autowired
    CaseteroRepository caseteroRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;
    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] { "id", "numberCasetero", "incremento" , "decremento",
        "actual", "dispensados", "depositados", "reject", "retract", "denominacion", "status",
        "presentados", "type", "currency", "device"};

    @InitBinder( "Casetero" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/{deviceId}/batch")
    public ResponseWrapper<Casetero> saveBatch(@RequestBody List<Casetero> caseteros, @PathVariable Integer deviceId)
    {
        return caseterosBusiness.saveBatch(caseteros,deviceId);
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper<Casetero> findAllCaseteros()
    {
        return responseFactory.buildResponseList(caseteroRepository.findAll(), Casetero.class, "");

    }

    @ResponseBody
    @GetMapping("/{ip:.+}")
    public ResponseWrapper<Casetero> findByAtmIp(@PathVariable String ip)
    {
        ResponseWrapper<Casetero> response;
        List<CaseterosEntity> entities = null;
        try {
            entities = caseteroRepository.findByDeviceAtmIp(streamFilter.sanitizeString(ip));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        ResponseWrapper<Casetero> caseteros = responseFactory.buildResponseList(entities, Casetero.class, ip);
        response = caseteros;
        if(caseteros.getCode().equals("00"))
        {

            response.setBody(caseteros.getBody().stream().sorted(Comparator.comparing(Casetero::getNumberCasetero)).collect(Collectors.toList()));

        }
        return response;
    }
}
