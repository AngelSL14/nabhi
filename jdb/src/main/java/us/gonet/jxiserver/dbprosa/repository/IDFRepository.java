package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.IDFEntity;

public interface IDFRepository extends JpaRepository<IDFEntity, String > {
    IDFEntity findByFiid(String fiid);
}
