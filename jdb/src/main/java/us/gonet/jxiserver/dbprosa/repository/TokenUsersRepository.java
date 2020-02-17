package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.TokenUsersEntity;

public interface TokenUsersRepository extends JpaRepository<TokenUsersEntity,String> {

}
