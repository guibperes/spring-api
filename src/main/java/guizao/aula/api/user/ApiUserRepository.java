package guizao.aula.api.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserRepository extends JpaRepository<ApiUser, String> {

  public ApiUser findByToken(String token);
}
