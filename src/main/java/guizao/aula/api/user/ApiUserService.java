package guizao.aula.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApiUserService {

  @Autowired
  private ApiUserRepository userRepo;

  @Autowired
  private BCryptPasswordEncoder crypt;

  public String register (ApiUser user) {
    user.setPassword(crypt.encode(user.getPassword()));
    ApiUser saved = userRepo.save(user);
    return saved.getId();
  }
}
