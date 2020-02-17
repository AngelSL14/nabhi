package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.TokenUsersEntity;
import us.gonet.jxiserver.dbprosa.repository.TokenUsersRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.security.auth.Crypto;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.TokenUser;

import java.util.Optional;

@RestController
@RequestMapping("/tokenUsers")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class TokenUserController {
    @Autowired
    TokenUsersRepository tokenUsersRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] {"id", "clave", "rol"};

    @InitBinder( "TokenUser" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @PostMapping()
    public ResponseWrapper<TokenUser> saveTokenUser(@RequestBody TokenUser tokenUser)
    {
        tokenUser.setClave((Crypto.getEncryption(tokenUser.getClave())));

        TokenUsersEntity entity = tokenUsersRepository.save(modelMapper.map(tokenUser, TokenUsersEntity.class));
        return responseFactory.buildResponseSingle(entity, TokenUser.class, tokenUser.toString());
    }

    @ResponseBody
    @GetMapping("/search/byId")
    public ResponseEntity findTokenById(@RequestParam String id)
    {
        Optional<TokenUsersEntity> token;
        try {
            token = tokenUsersRepository.findById(streamFilter.sanitizeString(id));
        } catch (ServerException e) {
            return ResponseEntity.ok(new TokenUsersEntity());
        }
        TokenUser response;
        if(token.isPresent())
        {
            response = modelMapper.map( token.get(), TokenUser.class);
        }
        else
        {
            return ResponseEntity.ok("");

        }
        return ResponseEntity.ok(response);
    }
}
