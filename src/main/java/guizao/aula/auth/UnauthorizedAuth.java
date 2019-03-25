package guizao.aula.auth;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class UnauthorizedAuth implements AuthenticationEntryPoint, Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public void commence (HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException {
    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized, you don't have permission to access");
  }
}
