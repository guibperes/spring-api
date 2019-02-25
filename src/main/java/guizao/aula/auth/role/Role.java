package guizao.aula.auth.role;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

  ROLE_API_ADMIN, ROLE_API_USER;

  @Override
  public String getAuthority () {
    return name();
  }
}
