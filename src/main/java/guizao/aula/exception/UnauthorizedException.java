package guizao.aula.exception;

public class UnauthorizedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException(String username) {
    super("Unauthorized login for username " + username);
  }
}
