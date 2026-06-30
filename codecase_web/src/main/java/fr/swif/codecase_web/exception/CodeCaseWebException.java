package fr.swif.codecase_web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class CodeCaseWebException extends Exception {

  private final HttpStatusCode statusCode;

  /**
   * Constructeur pour les exceptions métiers
   * @param statusCode Le code de status renvoyé
   * @param message le message renvoyé
   */
  public CodeCaseWebException(HttpStatusCode statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  /**
   * Constructeur pour ResourceAccessException, cette exception n'a pas de code
   * de status de base. Le code de status est mis en SERVICE_UNAVAILABLE (code
   * 503).
   * @param message Le message renvoyé
   */
  public CodeCaseWebException(String message) {
    super(message);
    this.statusCode = HttpStatus.SERVICE_UNAVAILABLE;
  }
}
