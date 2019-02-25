package guizao.aula.api.user;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import guizao.aula.config.RestConfig;
import guizao.aula.exception.BadRequestException;
import guizao.aula.exception.EntityNotFoundException;
import guizao.aula.utils.Login;
import guizao.aula.utils.Token;
import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@RestConfig
@Api(tags = "User")
@RequestMapping("/user")
public class ApiUserController {

  @Autowired
  private ApiUserService userService;

  @PostMapping("/register")
  public ResponseEntity<Token> register (@Valid @RequestBody ApiUser user, @ApiIgnore Errors err) {
    if (!err.hasErrors()) {
      Token token = userService.register(user);
      return new ResponseEntity<Token>(token, HttpStatus.CREATED);
    }
    throw new BadRequestException("User", err);
  }

  @PostMapping("/login")
  public ResponseEntity<Token> login (@Valid @RequestBody Login login, @ApiIgnore Errors err) {
    if (!err.hasErrors()) {
      Optional<Token> token = userService.login(login);
      if (token.isPresent()) {
        return new ResponseEntity<Token>(token.get(), HttpStatus.OK);
      }
      throw new EntityNotFoundException("User", login.getUsername() + ":" + login.getPassword());
    }
    throw new BadRequestException("User", err);
  }
}
