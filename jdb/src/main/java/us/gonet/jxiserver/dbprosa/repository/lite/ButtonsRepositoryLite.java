package us.gonet.jxiserver.dbprosa.repository.lite;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.lite.ButtonsEntityLite;

import java.util.List;


public interface ButtonsRepositoryLite extends JpaRepository<ButtonsEntityLite, Integer> {

    List<ButtonsEntityLite> findByAtm(String atm);




}
