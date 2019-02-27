package guizao.aula.log.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserLoginLogRepository extends JpaRepository<ApiUserLoginLog, String> {
}
