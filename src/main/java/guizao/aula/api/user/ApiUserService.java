package guizao.aula.api.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import guizao.aula.auth.role.Role;
import guizao.aula.exception.UnauthorizedException;
import guizao.aula.log.login.LoginLog;
import guizao.aula.log.login.LoginLogRepository;
import guizao.aula.utils.Login;
import guizao.aula.utils.Token;

@Service
public class ApiUserService {

  @Autowired
  private ApiUserRepository userRepo;

  @Autowired
  private LoginLogRepository userLoginLogRepo;

  @Autowired
  private BCryptPasswordEncoder crypt;

  public Token register (ApiUser user) {
    user.setPassword(crypt.encode(user.getPassword()));
    user.getRoles().add(Role.ROLE_API_USER.name());
    user.setToken(Token.generateNewToken());
    ApiUser savedUser = userRepo.save(user);
    return new Token(savedUser.getToken());
  }

  public Optional<ApiUser> findByToken (String token) {
    return userRepo.findByToken(token);
  }

  public Optional<Token> login (Login login) {
    Optional<ApiUser> user = userRepo.findByLogin(login.getLogin());
    if (user.isPresent()) {
      if (crypt.matches(login.getPassword(), user.get().getPassword())) {
        userLoginLogRepo.save(new LoginLog(user.get(), true));
        return Optional.of(new Token(user.get().getToken()));
      }
      userLoginLogRepo.save(new LoginLog(user.get(), false));
      throw new UnauthorizedException(login.getLogin());
    }
    return Optional.empty();
  }
}
