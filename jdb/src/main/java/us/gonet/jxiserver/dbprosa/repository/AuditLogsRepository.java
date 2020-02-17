package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.repository.CrudRepository;
import us.gonet.jxiserver.dbprosa.entity.AuditLogsEntity;

public interface AuditLogsRepository extends CrudRepository<AuditLogsEntity, Integer> {
}
