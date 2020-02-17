package us.gonet.jxiserver.dbprosa.esq;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmEsqRepository extends JpaRepository<AtmEsqEntity, String>{
    AtmEsqEntity findByTerminalId(String terminalId);
}
