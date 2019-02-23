package guizao.aula.utils;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Token {

  private String token;

  public static String generateNewToken () {
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    String timestamp = Long.toHexString(new Date().getTime());
    return uuid + "z" + timestamp;
  }
}
