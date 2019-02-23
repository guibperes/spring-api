package guizao.aula.api.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import guizao.aula.auth.Token;
import guizao.aula.config.RestConfig;
import io.swagger.annotations.Api;

@RestConfig
@Api(tags = "User")
@RequestMapping("/user")
public class ApiUserController {

  @Autowired
  private ApiUserService userService;

  @PostMapping("/register")
  public ResponseEntity<Token> register (@Valid @RequestBody ApiUser user) {
    Token token = userService.register(user);
    return new ResponseEntity<Token>(token, HttpStatus.CREATED);
  }
}
