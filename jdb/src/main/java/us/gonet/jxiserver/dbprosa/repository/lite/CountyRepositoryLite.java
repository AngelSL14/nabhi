package us.gonet.jxiserver.dbprosa.repository.lite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.gonet.jxiserver.dbprosa.entity.lite.CountyEntityLite;

@Repository
public interface CountyRepositoryLite extends JpaRepository<CountyEntityLite, Integer>{
}
