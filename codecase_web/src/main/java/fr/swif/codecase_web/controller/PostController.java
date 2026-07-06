package fr.swif.codecase_web.controller;

import fr.swif.codecase_web.exception.CodeCaseWebException;
import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.model.Technologie;
import fr.swif.codecase_web.service.LangageService;
import fr.swif.codecase_web.service.PostService;
import fr.swif.codecase_web.service.TechnologieService;
import fr.swif.codecase_web.service.UserService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  private final LangageService langageService;

  private final TechnologieService technologieService;

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
  public String formulaire(Model model) throws CodeCaseWebException{
    //! Faire en sorte de vérifier le token JWT du user avant toutes modifs
    model.addAttribute("post", new Post());
    model.addAttribute("langages", langageService.getLangages());
    model.addAttribute("technologies", technologieService.getTechnologies());
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
  public ModelAndView savePost(@ModelAttribute("post") @Valid Post post,
      BindingResult bindingResult,
      @RequestParam("idLangage") String langageId,
      @RequestParam("idTechnologie") String technologieId,
      @RequestParam(value = "nouvelleTechnologie", required = false)
      String nouvelleTechnologieIntitule)
      throws CodeCaseWebException {
    //! TEMPORAIRE POUR L'ID
    //! Faire en sorte de vérifier le token JWT du user avant toutes modifs
    post.setUserId(userService.getUser(1));

    post.setPostDateCreation(LocalDateTime.now());

    // Les if permettent de contrôler si les champs langageId et technologieId
    // (idLangage et idTechnologie dans la page creationPost), sont soit null ou
    // vides, si c'est l'un ou l'autre, ça ajoutera une erreur manuelle grâce au
    // rejectValue.
    // S'il n'y avait pas ceux-ci, cela retournerait une erreur de type
    // NumberFormatException à cause des value = "" dans le HTML
    if(langageId == null || langageId.isBlank()) {
      bindingResult.rejectValue("langageId", "langage.empty",
          "Veuillez sélectionner un langage");
    }

    boolean technologieIdVide = technologieId == null || technologieId.isBlank();
    boolean nouvelleTechnologieVide = nouvelleTechnologieIntitule == null
        || nouvelleTechnologieIntitule.isBlank();

    Technologie technologieACreer = null;

    // S'il n'y a rien de sélectionné dans la liste déroulante de
    // Outils & Technologies et que le champ "Nouvel Outil et/ou Technologie"
    // est vide, alors il y a un message d'erreur communiqué à l'utilisateur.
    if(technologieIdVide && nouvelleTechnologieVide) {
      bindingResult.rejectValue("technologieId",
          "technologie.empty",
          "Veuillez sélectionner un"
              + " Outils & Technologie ou en créer un nouveau");
    }
    // S'il n'y a rien de sélectionné dans la liste déroulante de
    // Outils & Technologies alors ça sauvegarde la saisie dans du champ
    // "Nouvel Outil et/ou Technologie".
    else if (technologieIdVide) {
      Technologie nouvelleTechnologie = new Technologie();
      nouvelleTechnologie.setTechnologieIntitule(nouvelleTechnologieIntitule);
      //! TEMPORAIRE POUR L'ID
      //! Faire en sorte que de vérifier le token JWT du user avant toutes modifs
      nouvelleTechnologie.setUserId(userService.getUser(1));
      technologieACreer =
          technologieService.createTechnologie(nouvelleTechnologie);
    } else {
      technologieACreer =
          technologieService.getTechnologie(Integer.parseInt(technologieId));
    }


    //! Important pour retourner la page initiale s'il y a des erreurs
    // Les addObject servent à recharger les méthodes getLangages et
    // getTechnologies, car sinon quand il y a une erreur liée à langageId ou
    // technologieId ceux-ci ne sont pas rechargés
    if(bindingResult.hasErrors()) {
      ModelAndView modelAndView = new ModelAndView("creationPost");
      modelAndView.addObject("langages",
          langageService.getLangages());
      modelAndView.addObject("technologies",
          technologieService.getTechnologies());
      return modelAndView;
    }

    post.setLangageId(langageService.getLangage(Integer.parseInt(langageId)));
    post.setTechnologieId(technologieACreer);

    //! Traiter les validations et les annotations (@Valid) pour que les erreurs n'arrivent pas jusqu'à l'api
    postService.createPost(post);
    return new ModelAndView("redirect:/");
  }
}
