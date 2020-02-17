package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.repository.CrudRepository;
import us.gonet.jxiserver.dbprosa.entity.SurchargeEntity;


public interface SurchargeRepository extends CrudRepository<SurchargeEntity, Integer> {
    SurchargeEntity findByFiidAcquirerAndAndFiidIssuingAndTranCode(String fiidAcquirer, String fiidIssuing, String tranCode);
}
