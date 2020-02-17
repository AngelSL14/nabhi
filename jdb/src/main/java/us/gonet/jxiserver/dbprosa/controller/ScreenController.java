package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.ScreenEntity;
import us.gonet.jxiserver.dbprosa.repository.AtmRepository;
import us.gonet.jxiserver.dbprosa.repository.ScreenRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Screen;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("screen")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class ScreenController {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    AtmRepository atmRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] SCREEN_ALLOW = new String[] {"atm", "screenType", "extPublicity", "namePublicity",
            "bankStyle"};

    @InitBinder( "Screen" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields(SCREEN_ALLOW);
    }

    @ResponseBody
    @PostMapping("/{idAtm}")
    public ResponseWrapper<Screen> save(@RequestBody Screen screen, @PathVariable("idAtm") String idAtm)
    {
        ScreenEntity entity = modelMapper.map(screen, ScreenEntity.class);
        entity.setId(idAtm);
        ScreenEntity entity1 = screenRepository.save(entity);
        return responseFactory.buildResponseSingle(entity1, Screen.class, idAtm);
    }

    @ResponseBody
    @PutMapping("/{ip:.+}/batch")
    public ResponseEntity updateBatch(@RequestBody List<Screen> screens, @PathVariable("ip") String ip)
    {
        List<ScreenEntity> entities = new ArrayList<>();
        for (Screen screen : screens)
        {
            ScreenEntity entity = modelMapper.map(screen, ScreenEntity.class);
            try {
                entity.setAtm(atmRepository.findByIp(streamFilter.sanitizeString(ip)));
            } catch (ServerException e) {
                return ResponseEntity.ok(new ArrayList<>());
            }
            entities.add(entity);
        }
        Iterable<ScreenEntity> entityIterator = screenRepository.saveAll(entities);
        return ResponseEntity.ok(entityIterator);
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper<Screen> findAllScreen()
    {
        List<Screen> screenList = modelMapper.map(screenRepository.findAll(), List.class);
        return responseFactory.buildResponseList(screenList, Screen.class, "");
    }

    @ResponseBody
    @GetMapping("/{ip:.+}")
    public ResponseWrapper<Screen> findByAtmIp(@PathVariable String ip)
    {
        ScreenEntity entity = null;
        try {
            entity = screenRepository.findByAtmIp(streamFilter.sanitizeString(ip));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        return responseFactory.buildResponseSingle(entity, Screen.class, ip);
    }
}
