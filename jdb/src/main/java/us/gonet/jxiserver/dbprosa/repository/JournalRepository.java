package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.repository.CrudRepository;
import us.gonet.jxiserver.dbprosa.entity.JournalEntity;

public interface JournalRepository extends CrudRepository<JournalEntity, String> {

}
