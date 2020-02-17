package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.ButtonsEntity;

import java.util.List;


public interface ButtonsRepository extends JpaRepository<ButtonsEntity, Integer> {

    List<ButtonsEntity> findByAtmIp(String ip);




}
