package us.gonet.jxiserver.dbprosa.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.entity.TokenUsersEntity;
import us.gonet.jxiserver.dbprosa.repository.TokenUsersRepository;
import us.gonet.security.rest.IUser;
import us.gonet.security.rest.model.User;
import us.gonet.serverutils.model.jdb.TokenUser;

import java.util.Optional;

@Primary
@Component
public class RepositorySecAccess implements IUser {

    private TokenUsersRepository repository;
    private ModelMapper mapper;

    @Autowired
    public RepositorySecAccess ( TokenUsersRepository repository, ModelMapper mapper ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User findById ( String id ) {
        Optional< TokenUsersEntity > entity = repository.findById( id );
        if ( entity.isPresent() ){
            TokenUser tokenUser = mapper.map( entity.get(), TokenUser.class );
            User user = new User();
            user.setId( tokenUser.getId() );
            user.setP( tokenUser.getClave() );
            user.setQ( ( byte ) tokenUser.getRol() );
            return user;
        }
        return null;
    }
}
