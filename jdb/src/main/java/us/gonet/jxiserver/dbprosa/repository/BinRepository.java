package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.repository.CrudRepository;
import us.gonet.jxiserver.dbprosa.entity.BinEntity;

public interface BinRepository extends CrudRepository<BinEntity, Integer> {
    BinEntity findByBin(String bin);
    BinEntity findByBinStartingWith(String bin);
}

