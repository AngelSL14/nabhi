package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.entity.RcptEntity;

@Component
public interface RcptRepository extends JpaRepository < RcptEntity, String> {
}
