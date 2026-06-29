package fr.swif.codecase_web.controller;

import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.service.PostService;
import fr.swif.codecase_web.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * PostRestController
 * <i>de fr.swif.codecase_web.controller</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 22/06/2026
 */


// @Controller qui marque la classe comme composant Spring MVC
// gérant les requêtes HTTP
@Controller
// @RequiredArgsConstructor génère automatiquement un constructeur prenant
// en paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  private final UserService userService;

  /**
   * Méthode formulaire
   *
   *<i>de PostController</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant d'accéder à la page creationPost</p>
   * @param model Le Post attendant d'être créé
   * @return La page creationPost
   */
  @GetMapping("/creationPost")
  public String formulaire(Model model) {
    //! Faire en sorte que de vérifier le token JWT du user avant toutes modifs
    model.addAttribute("post", new Post());
    return "creationPost";
  }

  /**
   * Méthode savePost
   *
   *<i>de PostController</i>
   *<h1></h1>
   *<hr>
   *<p>Méthode permettant de créer un Post (sauvegarder) et de rediriger sur
   * la page d'accueil</p>
   * @param post L'id du Post et sa date de création
   * @return La page d'accueil
   */
  @PostMapping("/createPost")
  public ModelAndView savePost(@ModelAttribute("post") Post post) {
    //! TEMPORAIRE POUR L'ID
    //! Faire en sorte que de vérifier le token JWT du user avant toutes modifs
    post.setUserId(userService.getUser(1));
    post.setPostDateCreation(LocalDateTime.now());

    postService.createPost(post);
    return new ModelAndView("redirect:/");
  }
}
