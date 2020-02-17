package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.DashboardUsersEntity;

public interface DashboardUserRepository extends JpaRepository<DashboardUsersEntity, Integer> {
    DashboardUsersEntity findByEmailAndPassword(String email, String password);
    DashboardUsersEntity findByEmail(String email);

}
