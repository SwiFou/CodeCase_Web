package fr.swif.codecase_web.service;

import fr.swif.codecase_web.exception.CodeCaseWebException;
import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

/**
 * PostService
 * <i>de fr.swif.codecase_web.service</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 22/06/2026
 */

// @Slf4j permet de générer un champ de log
@Slf4j
// @Service sert à indiquer que la classe détient la logique métier
@Service
// @RequiredArgsConstructor génère automatiquement un constructeur prenant en
// paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  /**
   * Méthode getPosts
   *
   *<i>de PostService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie tous les Posts en BDD</p>
   * @return Un Iterable de Post
   * @throws CodeCaseWebException
   */
  public Iterable<Post> getPosts() throws CodeCaseWebException {
    try {
      return postRepository.getPosts();
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Impossible de "
          + "récupérer la liste des posts.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors du chargement des posts. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

  /**
   * Méthode getPost
   *
   *<i>de PostService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie un Post avec l'id spécifié</p>
   * @param id L'id du Post cherché
   * @return Le Post
   * @throws CodeCaseWebException
   */
  public Post getPost(int id) throws CodeCaseWebException{
    try {
      return postRepository.getPost(id);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Le post demandé "
          + "n'existe pas ou n'est plus disponible.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la récupération du post. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

  /**
   * Méthode deletePost
   *
   *<i>de PostService</i>
   *<h1></h1>
   *<hr>
   *<p>Supprime le Post avec l'id spécifié, ne renvoie rien</p>
   * @param id L'id du Post à supprimer
   * @throws CodeCaseWebException
   */
  public void deletePost(int id) throws CodeCaseWebException{
    try {
      postRepository.deletePost(id);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Ce post n'existe pas "
          + "ou a déjà été supprimé.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la suppression du post. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Une erreur est survenue lors de la "
          + "suppression du post.");
    }
  }

  /**
   * Méthode createPost
   *
   *<i>de PostService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie le Post une fois créé dans la BDD</p>
   * @param post Le Post à créer
   * @return Le Post créé
   * @throws CodeCaseWebException
   */
  public Post createPost(Post post) throws CodeCaseWebException{
    try {
      return postRepository.createPost(post);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Votre demande n'a "
          + "pas pu être traitée. Merci de vérifier les informations saisies.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la publication de votre post. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Une erreur est survenue lors de la "
          + "publication de votre post.");
    }
  }

}
