package guizao.aula.api.user;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import guizao.aula.auth.role.annotation.RoleApiUser;
import guizao.aula.config.RestConfig;
import guizao.aula.exception.BadRequestException;
import guizao.aula.exception.EntityNotFoundException;
import guizao.aula.utils.Login;
import guizao.aula.utils.Token;
import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.web.bind.annotation.GetMapping;

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

  @RoleApiUser
  @GetMapping
  public ResponseEntity<ApiUser> get () {
    String token = ((ApiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken();
    Optional<ApiUser> user = userService.findByToken(token);
    return new ResponseEntity<ApiUser>(user.get(), HttpStatus.OK);
  }
}
