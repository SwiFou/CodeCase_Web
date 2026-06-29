package fr.swif.codecase_web.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionManagerWeb
 * <i>de fr.swif.codecase_web.exception</i>
 * <hr>
 * <p>Gestionnaire des Exceptions, dédié à rassembler la gestion des exceptions
 * majeures et centralisables côté Web</p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 29/06/2026
 */

// 0ControllerAdvice permet d'intercepter les exceptions levées par les
// controllers et ainsi de retourner n'importe quel type de réponse, ici des
// vues Thymeleaf
@ControllerAdvice
public class ExceptionManagerWeb {

  /**
   * Méthode handleCodeCaseWebException
   * (CodeCaseWebException codeCaseWebException, Model model)
   *
   *<i>de ExceptionManagerWeb</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant de renvoyer le code status et le message d'erreur des
   * exceptions métier de type CodeCaseWebException</p>
   * @param codeCaseWebException Le type d'exception
   * @param model Le code status et le message renvoyé
   * @return La page erreur
   */
  // @ExceptionHandler permet de définir la logique pour traiter et répondre aux
  // exceptions traitées en paramètre
  @ExceptionHandler(CodeCaseWebException.class)
  public String handleCodeCaseWebException(CodeCaseWebException codeCaseWebException, Model model) {
    model.addAttribute("codeStatus", codeCaseWebException.getStatus());
    model.addAttribute("message", codeCaseWebException.getMessage());

    return "erreur";
  }



  /**
   * Méthode handleExceptions(Exception exception, Model model)
   *
   *<i>de ExceptionManagerWeb</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode qui gère toutes les exceptions non attrapées par les méthodes
   * précédentes</p>
   * @param exception Le type d'exception
   * @param model Le message renvoyé
   * @return La page erreur
   */
  // @ExceptionHandler permet de définir la logique pour traiter et répondre aux
  // exceptions traitées en paramètre
  @ExceptionHandler(Exception.class)
  public String handleExceptions(Exception exception, Model model) {
    model.addAttribute("message", exception.getMessage());

    return "erreur";
  }
}
