package fr.swif.codecase_web.controller;

import fr.swif.codecase_web.model.User;
import fr.swif.codecase_web.service.PostService;
import fr.swif.codecase_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * EspaceController
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
public class EspaceUserController {

  private final UserService userService;

  private final PostService postService;

  @GetMapping("/espaceUser")
  public String login(@RequestParam String userMail, Model model) {
    //! Faire en sorte que de vérifier le token JWT du user avant toutes modifs
    User user = userService.getUserByMail(userMail);
    model.addAttribute("user", user);

    return "espaceUser";
  }

  // Utilisation de @PostMapping malgré le fait que ce soit une modification,
  // car les formulaires en HTML classiques ne supprortent que les méthodes GET
  // et POST
  @PostMapping("/saveEmail")
  public ModelAndView saveEmail(@ModelAttribute("user") User user,
      RedirectAttributes redirectAttributes) {
    //! Faire en sorte que de vérifier le token JWT du user avant toutes modifs
    user.setUserEmail(user.getUserEmail());
    userService.updateUser(user);

    // addFlashAttribute permet de sauvegarder un message avant la redirection
    // de la page, n'est disponible qu'une seule fois puis est automatiquement
    // supprimé
    redirectAttributes.addFlashAttribute("message", "Email modifié avec succès");
    return new ModelAndView("redirect:/espaceUser");
  }

  // Utilisation de @PostMapping malgré le fait que ce soit une modification,
  // car les formulaires en HTML classiques ne supprortent que les méthodes GET
  // et POST
  @PostMapping("/saveMdp")
  public ModelAndView saveMdp(@ModelAttribute("user") User user,
      RedirectAttributes redirectAttributes) {
    //! Faire en sorte que de vérifier le token JWT du user avant toutes modifs
    user.setUserMdp(user.getUserMdp());
    userService.updateUser(user);

    // addFlashAttribute permet de sauvegarder un message avant la redirection
    // de la page, n'est disponible qu'une seule fois puis est automatiquement
    // supprimé
    redirectAttributes.addFlashAttribute("message", "Mot de passe modifié avec succès");
    return new ModelAndView("redirect:/espaceUser");
  }
}
