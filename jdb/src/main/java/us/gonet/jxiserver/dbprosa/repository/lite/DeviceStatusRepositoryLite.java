package us.gonet.jxiserver.dbprosa.repository.lite;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.lite.DeviceStatusEntityLite;

import java.util.List;

public interface DeviceStatusRepositoryLite extends JpaRepository<DeviceStatusEntityLite, Integer> {
    List<DeviceStatusEntityLite> findByAtm(String atm);
}
