package guizao.aula.exception;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CustomError {

  private LocalDateTime timestamp;
  private int status;
  private String error;
  private String message;

  public CustomError (int status, String error, String message) {
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.error = error;
    this.message = message;
  }
}
