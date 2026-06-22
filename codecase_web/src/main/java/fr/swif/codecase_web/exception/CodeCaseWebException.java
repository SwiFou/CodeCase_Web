package fr.swif.codecase_web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CodeCaseWebException extends Exception {

  private final HttpStatus status;

  public CodeCaseWebException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
