
package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.CaseterosEntity;

import java.util.List;

public interface CaseteroRepository extends JpaRepository<CaseterosEntity, Integer> {

    List<CaseterosEntity> findByDeviceAtmIp(String ip);
    List<CaseterosEntity> findByDeviceAtmId (String id);
    List<CaseterosEntity> findByDeviceId(int id);
}

