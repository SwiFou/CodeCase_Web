package fr.swif.codecase_web.controller;

import fr.swif.codecase_web.exception.CodeCaseWebException;
import fr.swif.codecase_web.model.User;
import fr.swif.codecase_web.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * AuthWebController
 * <i>de fr.swif.codecase_web.controller</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 16/07/2026
 */

// @Controller qui marque la classe comme composant Spring MVC
// gérant les requêtes HTTP
@Controller
// @RequiredArgsConstructor génère automatiquement un constructeur prenant
// en paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class AuthWebController {

  private final AuthService authService;

  /**
   * Méthode afficherConnexion
   *
   *<i>de AuthWebController</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant d'accéder à la page connexion</p>
   * @return La page connexion
   */
  @GetMapping("/connexion")
  public String afficherConnexion() {
    return "connexion";
  }

  /**
   * Méthode connexion
   *
   *<i>de AuthWebController</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant de connecter un User via l'API et de relayer
   * le cookie JWT reçu vers le navigateur, avant de rediriger sur la
   * page d'accueil</p>
   * @param user L'email et le mot de passe saisis dans le formulaire
   * @param response La réponse HTTP servant à relayer le cookie JWT
   * @return La page d'accueil, ou la page connexion en cas d'erreur
   */
  @PostMapping("/connexion")
  public ModelAndView connexion(@ModelAttribute("user") User user,
      HttpServletResponse response) throws CodeCaseWebException {

    authService.connecterUser(user, response);

    return new ModelAndView("redirect:/");
  }

  /**
   * Méthode afficherInscription
   *
   *<i>de AuthWebController</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant d'accéder à la page inscription</p>
   * @param model Le User attendant d'être créé
   * @return La page inscription
   */
  @GetMapping("/inscription")
  public String afficherInscription(Model model) {
    model.addAttribute("user", new User());
    return "inscription";
  }

  /**
   * Méthode inscription
   *
   *<i>de AuthWebController</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant d'inscrire un User via l'API (rôle MEMBRE forcé
   * côté API) et de relayer le cookie JWT reçu vers le navigateur, avant
   * de rediriger sur la page d'accueil</p>
   * @param user Le User à créer, saisi dans le formulaire
   * @param bindingResult Les erreurs de validation du formulaire
   * @param response La réponse HTTP servant à relayer le cookie JWT
   * @return La page d'accueil, ou la page inscription en cas d'erreur
   */
  @PostMapping("/inscription")
  public ModelAndView inscription(@ModelAttribute("user") @Valid User user,
      BindingResult bindingResult,
      HttpServletResponse response) throws CodeCaseWebException {

    if (bindingResult.hasErrors()) {
      return new ModelAndView("inscription");
    }

    authService.inscrireUser(user, response);

    return new ModelAndView("redirect:/");
  }

  /**
   * Méthode deconnexion
   *
   *<i>de AuthWebController</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant de déconnecter le User courant en invalidant
   * le cookie JWT côté API et côté navigateur, avant de rediriger sur
   * la page connexion</p>
   * @param response La réponse HTTP servant à invalider le cookie JWT
   * @return La page connexion
   */
  @PostMapping("/deconnexion")
  public ModelAndView deconnexion(HttpServletResponse response)
      throws CodeCaseWebException {

    authService.deconnecterUser(response);

    return new ModelAndView("redirect:/connexion");
  }
}
