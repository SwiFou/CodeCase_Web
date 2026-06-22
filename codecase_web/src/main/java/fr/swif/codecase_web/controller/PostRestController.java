package fr.swif.codecase_web.controller;

import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.service.PostService;
import fr.swif.codecase_web.service.UserService;
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
public class PostRestController {

  private final PostService postService;

  private final UserService userService;

  @GetMapping("/creationPost")
  public String formulaire(Model model) {
    model.addAttribute("post", new Post());
    return "creationPost";
  }

  @PostMapping("/createPost")
  public ModelAndView savePost(@ModelAttribute("post") Post post) {

  }
}
