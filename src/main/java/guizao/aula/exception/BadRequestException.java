package guizao.aula.exception;

import org.springframework.validation.Errors;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BadRequestException (String entityName, Errors err) {
    super("Errors on fields " + err.getFieldErrors() + " on entity " + entityName);
  }
}
