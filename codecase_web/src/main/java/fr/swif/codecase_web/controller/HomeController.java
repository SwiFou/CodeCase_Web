package fr.swif.codecase_web.controller;

import fr.swif.codecase_web.exception.CodeCaseWebException;
import fr.swif.codecase_web.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController
 * <i>de fr.swif.codecase_web.controller</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 23/06/2026
 */

// @Controller qui marque la classe comme composant Spring MVC
// gérant les requêtes HTTP
@Controller
// @RequiredArgsConstructor génère automatiquement un constructeur prenant
// en paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class HomeController {

  private final PostService postService;

  /**
   * Méthode home
   *
   *<i>de HomeController</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant d'accéder à la page d'accueil</p>
   * @param model Les posts créés
   * @return La page d'accueil
   */
  @GetMapping("/")
  public String home(Model model) throws CodeCaseWebException {
    model.addAttribute("Posts", postService.getPosts());
    return "index";
  }

}
