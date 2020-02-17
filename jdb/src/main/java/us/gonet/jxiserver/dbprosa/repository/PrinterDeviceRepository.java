package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.repository.CrudRepository;
import us.gonet.jxiserver.dbprosa.entity.PrinterEntity;

public interface PrinterDeviceRepository extends CrudRepository<PrinterEntity, Integer>{

    PrinterEntity findByDeviceId(int id);
    
}
