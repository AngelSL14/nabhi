package us.gonet.jxiserver.dbprosa.repository.lite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.gonet.jxiserver.dbprosa.entity.lite.JournalEntityLite;

import java.util.List;

@Repository
public interface JournalRepositoryLite extends JpaRepository<JournalEntityLite, Integer> {
    List<JournalEntityLite> findByAtm(String atm);
}
