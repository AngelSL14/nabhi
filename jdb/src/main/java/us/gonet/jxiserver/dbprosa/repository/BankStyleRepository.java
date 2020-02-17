package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.BankStyleEntity;

public interface BankStyleRepository extends JpaRepository<BankStyleEntity, String> {
    @Override
    <S extends BankStyleEntity> S save(S s);
}
