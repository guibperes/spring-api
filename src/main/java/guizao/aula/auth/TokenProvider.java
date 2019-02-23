package guizao.aula.auth;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

  public String generateNewToken () {
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    String timestamp = Long.toHexString(new Date().getTime());
    return uuid + "z" + timestamp;
  }
}
