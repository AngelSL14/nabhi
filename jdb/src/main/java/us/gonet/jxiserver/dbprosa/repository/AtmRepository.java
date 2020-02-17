package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.AtmEntity;

public interface AtmRepository extends JpaRepository<AtmEntity, String> {

    AtmEntity findByIp (String ip);



}
