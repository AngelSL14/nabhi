
package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.DashboardUsersEntity;
import us.gonet.jxiserver.dbprosa.repository.DashboardUserRepository;
import us.gonet.jxiserver.dbprosa.response.ErrorWS;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.security.auth.Crypto;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.DashboardUser;

@RestController
@RequestMapping("dashboard")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class DashboardUsersController {

    @Autowired
    DashboardUserRepository dashboardUserRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;

    @InitBinder( "DashboardUser" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields(DASH_USERS_FIELDS);
    }

    private static final String[] DASH_USERS_FIELDS = new String[] {"idUser", "password", "phoneNumber", "email", "fullname"};



    @ResponseBody
    @GetMapping("{email:.+}")
    public ResponseWrapper<DashboardUser> findUser(@PathVariable String email)
    {
        DashboardUsersEntity mode = null;
        try {
            mode = dashboardUserRepository.findByEmail(streamFilter.sanitizeString(email));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }

        return responseFactory.buildResponseSingle(mode, DashboardUser.class, email);
    }

    @ResponseBody
    @PostMapping()
    public ResponseWrapper<DashboardUser> saveUser(@RequestBody DashboardUser user)
    {
        ResponseWrapper<DashboardUser> response;
        user.setPassword(Crypto.getEncryption(user.getPassword()));
        DashboardUsersEntity entity = modelMapper.map(user, DashboardUsersEntity.class);
        try {
            String email = streamFilter.sanitizeString(user.getEmail());
            if(dashboardUserRepository.findByEmail(email)!= null)
            {
                response = new ResponseWrapper<>();
                response.setCode("01");
                response.addError( new ErrorWS("JDB-02","El registro ya existe: "+ user.getIdUser() + ":" + user.getEmail() ));
            }
            else
            {
                response =  responseFactory.buildResponseSingle(dashboardUserRepository.save(entity), DashboardUser.class, user.toString());
            }
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }

        return response;
    }

    @ResponseBody
    @PutMapping()
    public ResponseWrapper<DashboardUser> updateUser(@RequestBody DashboardUser user)
    {
        user.setPassword(Crypto.getEncryption(user.getPassword()));
        ResponseWrapper<DashboardUser> response;
        DashboardUsersEntity entity = modelMapper.map(user, DashboardUsersEntity.class);
        try {
            String email = streamFilter.sanitizeString(user.getEmail());
            if(user.getIdUser()!=null && dashboardUserRepository.findByEmail(email)!= null)
            {
                response =  responseFactory.buildResponseSingle(dashboardUserRepository.save(entity), DashboardUser.class, user.toString());
            }
            else
            {
                response = new ResponseWrapper<>();
                response.addError(new ErrorWS("JDB03", "El usuario que intenta actualizar no existe, id: "+user.getIdUser()));
            }
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }

        return response;
    }



    @ResponseBody
    @GetMapping("{email}/{password}")
    public ResponseWrapper<DashboardUser> findUser(@PathVariable String email, @PathVariable String password)
    {
        DashboardUsersEntity mode = null;
        try {
            mode = dashboardUserRepository.findByEmailAndPassword(streamFilter.sanitizeString(email),
                    streamFilter.sanitizeString(password));
        } catch (ServerException e) {
            streamFilter.sanitizeError();
        }
        return responseFactory.buildResponseSingle(mode, DashboardUser.class, email);
    }



}
