package guizao.aula.api.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserRepository extends JpaRepository<ApiUser, String> {

  public ApiUser findByToken(String token);

  public Optional<ApiUser> findByLogin(String login);
}
