package fr.swif.codecase_web.repository;

import fr.swif.codecase_web.config.CustomProperties;
import fr.swif.codecase_web.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * PostRepository
 * <i>de fr.swif.codecase_web.repository</i>
 * <hr>
 * <p>Repository qui fait le lien entre l'api et la webapp de Post</p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 19/06/2026
 */

// @Slf4j permet de générer un champ de log
@Slf4j
// @Component est une annotation Spring qui marque une classe comme bean géré
// par le conteneur IoC (Inversion of Control) de Spring
// Spring scan tous les packages et instancie automatiquement toutes les classes
// annotées @Component
@Component
// @RequiredArgsConstructor génère automatiquement un constructeur prenant en
// paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class PostRepository {

  private final CustomProperties props;

  /**
   * Méthode getPosts
   *
   *<i>de PostRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie tous les Posts en BDD</p>
   * @return un Iterable de Post
   */
  public Iterable<Post> getPosts() {
    String BASE_API_UL = props.getApiUrl();
    String getPostsUrl = BASE_API_UL + "/posts";

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Iterable<Post>> response = restTemplate.exchange(
        getPostsUrl, // L'URL
        HttpMethod.GET, // La méthode HTTP
        null, // La requête (requestEntity) qui peut renvoyer un Corps+Header ou rien
        new ParameterizedTypeReference<>() {} // le type de retour ici ParameterizedTypeReference car c'est un Iterable
    );
    log.debug("Get Posts " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode getPost
   *
   *<i>de PostRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie un Post avec l'id spécifié</p>
   * @param id L'id du Post cherché
   * @return Le Post
   */
  public Post getPost(int id) {
    String BASE_API_URL = props.getApiUrl();
    String getPostUrl = BASE_API_URL + "/post/" + id;

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Post> response = restTemplate.exchange(
        getPostUrl, // L'URL
        HttpMethod.GET, // La méthode HTTP
        null, // La requête (requestEntity) qui peut renvoyer un Corps+Header ou rien
        Post.class // le type de retour ici Post.class car c'est un objet simple
    );

    log.debug("Get Post " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode createPost
   *
   *<i>de PostRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Remvoie le Post une fois créé dans la BDD</p>
   * @param post Le Post à créer
   * @return Le Post créé
   */
  public Post createPost(Post post) {
    String BASE_API_URL = props.getApiUrl();
    String createPostUrl = BASE_API_URL + "/post";

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // HttpEntity est un objet qui retourne un Body et Headers
    HttpEntity<Post> request = new HttpEntity<>(post);
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Post> response = restTemplate.exchange(
        createPostUrl, // L'URL
        HttpMethod.POST, // La méthode HTTP
        request, // Le HttpEntity
        Post.class // le type de retour ici Post.class car c'est un objet simple
    );

    log.debug("Create Post " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode deletePost
   *
   *<i>de PostRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Supprime le Post avec l'id spécifié, ne renvoie rien</p>
   * @param id L'id du Post à supprimer
   */
  public void deletePost(int id) {
    String BASE_API_URL = props.getApiUrl();
    String deletePostUrl = BASE_API_URL + "/post/" + id;

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Post> response = restTemplate.exchange(
        deletePostUrl,
        HttpMethod.DELETE,
        null,
        Post.class
    );

    log.debug("Delete Post " + response.getStatusCode());
  }

}
