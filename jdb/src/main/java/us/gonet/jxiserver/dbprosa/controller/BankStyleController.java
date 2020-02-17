package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.BankStyleEntity;
import us.gonet.jxiserver.dbprosa.repository.BankStyleRepository;
import us.gonet.jxiserver.dbprosa.repository.IDFRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.BankStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bankStyle")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class BankStyleController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IDFRepository idfRepository;

    @Autowired
    BankStyleRepository bankStyleRepository;

    @Autowired
    ResponseFactory responseFactory;
    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] { "id", "buttons", "dashboard",
            "sections"};

    @InitBinder( "BankStyle" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }



    @ResponseBody
    @GetMapping()
    public ResponseWrapper<BankStyle> findAllBankStyles()
    {
        List<BankStyle> mode = modelMapper.map(bankStyleRepository.findAll(), List.class);
        return responseFactory.buildResponseList(mode, BankStyle.class, "");
    }

    @ResponseBody
    @PostMapping("/batch")
    public ResponseWrapper<BankStyle> saveBatchBankStyles(@RequestBody List<BankStyle> bankStyles)
    {
        List<BankStyleEntity> entities = new ArrayList<>();
        for(BankStyle bankStyle : bankStyles)
        {
            BankStyleEntity entity = modelMapper.map(bankStyle, BankStyleEntity.class);
            entities.add(entity);

        }

        return responseFactory.buildResponseList(entities, BankStyle.class, bankStyles.toString());
    }


    @ResponseBody
    @PostMapping()
    public ResponseWrapper<BankStyle> saveBankStyle(@RequestBody BankStyle bankStyle)
    {
       BankStyleEntity en = bankStyleRepository.save(modelMapper.map(bankStyle, BankStyleEntity.class));
        return responseFactory.buildResponseSingle(en, BankStyle.class, bankStyle.toString());
    }

    @ResponseBody
    @GetMapping("/{bank:.+}")
    public ResponseWrapper<BankStyle> findByBank(@PathVariable String bank)
    {
        ResponseWrapper<BankStyle> response;

        Optional<BankStyleEntity> opt;
        try {
            opt = bankStyleRepository.findById(streamFilter.sanitizeString(bank));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        if(opt.isPresent())
        {
            response = responseFactory.buildResponseSingle(opt.get(), BankStyle.class, bank);
        }
        else
        {
            response = responseFactory.buildResponseSingle(null, BankStyle.class, bank);
        }


        return response;
    }


}
