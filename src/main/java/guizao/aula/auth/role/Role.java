package guizao.aula.auth.role;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

  API_ADMIN, API_USER;

  @Override
  public String getAuthority () {
    return name();
  }
}
