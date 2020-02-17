package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import us.gonet.jxiserver.dbprosa.entity.ATDEntity;

import java.util.List;


public interface ATDRepository extends CrudRepository<ATDEntity, String > {

    @Override
    List <ATDEntity> findAll();

    @Query("select tatd from ATDEntity tatd where tatd.atm.ip = :ip")
    ATDEntity findAtdByIp(@Param("ip") String ip);

}
