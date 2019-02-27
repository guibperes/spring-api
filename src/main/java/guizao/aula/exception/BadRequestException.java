package guizao.aula.exception;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BadRequestException (String entityName, Errors err) {
    super("Errors on fields " + handleErrorFields(err.getFieldErrors()) + " on entity " + entityName);
  }

  private static String handleErrorFields (List<FieldError> errors) {
    String data = "";
    for (FieldError err : errors) {
      data += "(Field=" + err.getField() + " on object " + err.getObjectName().substring(0, 1).toUpperCase() + err.getObjectName().substring(1) + "), ";
    }
    return data.substring(0, data.length() - 2);
  }
}
