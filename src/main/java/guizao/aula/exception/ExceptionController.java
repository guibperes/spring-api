package guizao.aula.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<CustomError> handlerEntityNotFound (EntityNotFoundException exception) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError err = new CustomError(
      status.value(),
      status.toString(),
      exception.getMessage()
    );
    return new ResponseEntity<CustomError>(err, status);
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<CustomError> handlerBadRequest (BadRequestException exception) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    CustomError err = new CustomError(
      status.value(),
      status.toString(),
      exception.getMessage()
    );
    return new ResponseEntity<CustomError>(err, status);
  }
}
