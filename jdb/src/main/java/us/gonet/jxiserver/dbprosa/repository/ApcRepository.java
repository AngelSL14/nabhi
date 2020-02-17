package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.ApcEntity;


public interface ApcRepository extends JpaRepository<ApcEntity, String > {

    ApcEntity findByKey(String key);

    ApcEntity findByKeyAndIdfFiid(String key, String fkIdfFiid);

}
