package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.business.IButtonsBusiness;
import us.gonet.jxiserver.dbprosa.entity.ButtonsEntity;
import us.gonet.jxiserver.dbprosa.entity.lite.ButtonsEntityLite;
import us.gonet.jxiserver.dbprosa.repository.ButtonsRepository;
import us.gonet.jxiserver.dbprosa.repository.lite.ButtonsRepositoryLite;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Button;
import us.gonet.serverutils.model.jdb.lite.ButtonLite;

import java.util.List;

@RestController
@RequestMapping("buttons")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class ButtonsController {

    @Autowired @Qualifier("buttonsBus")
    IButtonsBusiness buttonsBusiness;

    @Autowired
    ButtonsRepository buttonsRepository;

    @Autowired
    ButtonsRepositoryLite buttonsRepositoryLite;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;
    @Autowired
    StreamFilter streamFilter;


    private static final String[] ALLOWED_FIELDS = new String[] { "id", "atm", "screen" , "activeFdk"};

    @InitBinder( "Button" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @PostMapping("/{atmId}/batch")
    public ResponseWrapper<Button> saveBatch(@RequestBody List<Button> buttons, @PathVariable String atmId)
    {
        return buttonsBusiness.saveBatch(buttons,atmId);
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper<Button> findAllButtons()
    {
        return responseFactory.buildResponseList(buttonsRepository.findAll(), Button.class, "");
    }

    @ResponseBody
    @GetMapping("/lite")
    public ResponseWrapper<ButtonLite> findAllButtonsLite()
    {
        return responseFactory.buildResponseList(buttonsRepository.findAll(), ButtonLite.class, "");
    }

    @ResponseBody
    @PostMapping()
    public ResponseWrapper<Button> saveButton(@RequestBody  Button button)
    {
        ButtonsEntity entity = modelMapper.map(button, ButtonsEntity.class);
        return responseFactory.buildResponseSingle(buttonsRepository.save(entity), Button.class, button.toString());
    }

    @ResponseBody
    @PostMapping("/lite")
    public ResponseWrapper<Button> saveButtonLite(@RequestBody ButtonLite button)
    {
        ButtonsEntityLite entity = modelMapper.map(button, ButtonsEntityLite.class);
        return responseFactory.buildResponseSingle(buttonsRepositoryLite.save(entity), Button.class, button.toString());
    }

    @ResponseBody
    @GetMapping("/{ip:.+}")
    public ResponseWrapper<Button> findByAtmIp(@PathVariable String ip)
    {
        List<ButtonsEntity> entities = null;
        try {
            entities = buttonsRepository.findByAtmIp(streamFilter.sanitizeString(ip));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        return responseFactory.buildResponseList(entities, Button.class, ip);
    }



}
