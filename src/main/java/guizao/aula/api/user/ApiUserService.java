package guizao.aula.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import guizao.aula.auth.Token;
import guizao.aula.auth.TokenProvider;
import guizao.aula.auth.role.Role;

@Service
public class ApiUserService {

  @Autowired
  private ApiUserRepository userRepo;

  @Autowired
  private BCryptPasswordEncoder crypt;

  @Autowired
  private TokenProvider tokenProvider;

  public Token register (ApiUser user) {
    user.setPassword(crypt.encode(user.getPassword()));
    user.getRoles().add(Role.API_USER.name());
    user.setToken(tokenProvider.generateNewToken());
    ApiUser savedUser = userRepo.save(user);
    return new Token(savedUser.getToken());
  }
}
