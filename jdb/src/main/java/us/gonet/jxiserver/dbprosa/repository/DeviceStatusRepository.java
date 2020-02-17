package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.DeviceStatusEntity;

import java.util.List;

public interface DeviceStatusRepository extends JpaRepository<DeviceStatusEntity, Integer> {
    DeviceStatusEntity findByAtmIdAndDeviceType(String atmId, String deviceType);
    List<DeviceStatusEntity> findByAtmId(String atm);
}
