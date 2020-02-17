package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.NodeProsaEntity;

public interface NodeProsaRepository extends JpaRepository<NodeProsaEntity, Integer> {

    NodeProsaEntity findByNodeName (String nodeName);

    NodeProsaEntity findByIdNodeAndNodeName(int idNode, String nodeName);
}
