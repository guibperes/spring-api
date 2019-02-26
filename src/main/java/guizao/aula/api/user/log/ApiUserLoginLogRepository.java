package guizao.aula.api.user.log;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserLoginLogRepository extends JpaRepository<ApiUserLoginLog, String> {
}
