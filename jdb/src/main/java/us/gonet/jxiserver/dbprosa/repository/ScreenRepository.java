package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.repository.CrudRepository;
import us.gonet.jxiserver.dbprosa.entity.ScreenEntity;

public interface ScreenRepository extends CrudRepository<ScreenEntity, String> {
    ScreenEntity findByAtmIp(String ip);
}
