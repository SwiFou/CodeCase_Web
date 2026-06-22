package fr.swif.codecase_web.repository;

import fr.swif.codecase_web.config.CustomProperties;
import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.model.User;
import java.util.prefs.BackingStoreException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * UserRepository
 * <i>de fr.swif.codecase_web.repository</i>
 * <hr>
 * <p>Repository qui fait le lien entre l'api et la webapp de User</p>
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
public class UserRepository {

  /**
   * Les propriétés customs
   */
  private final CustomProperties props;

  /**
   * Méthode getUsers
   *
   *<i>de UserRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie tous les Users en BDD</p>
   * @return Un Iterable de User
   */
  public Iterable<User> getUsers() {
    String BASE_API_URL = props.getApiUrl();
    String getUsersUrl = BASE_API_URL + "/users";

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Iterable<User>> response = restTemplate.exchange(
        getUsersUrl, // L'URL
        HttpMethod.GET, // La méthode HTTP
        null, // La requestEntity qui peut renvoyer un Corps+Header ou rien
        new ParameterizedTypeReference<>() {} // le type de retour ici ParameterizedTypeReference car c'est un Iterable
    );
    log.debug("Get Users " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode getUser
   *
   *<i>de UserRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie un User avec l'id spécifié</p>
   * @param id L'id du User recherché
   * @return Le User
   */
  public User getUser(int id) {
    String BASE_API_URL = props.getApiUrl();
    String getUserUrl = BASE_API_URL + "/user/" + id;

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant L'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntíty est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<User> response= restTemplate.exchange(
        getUserUrl, // L'URL
        HttpMethod.GET, // La méthode HTTP
        null, // La requestEntity qui peut renvoyer un Corps+Header ou rien
        User.class // Le type de retour ici User.class car c'est un objet simple
    );

    log.debug("Get User " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }


  public User getUserByPseudo(String pseudo) {
    String BASE_API_URL = props.getApiUrl();
    String getUserByPseudoUrl = BASE_API_URL + "/userPseudo/" + pseudo;

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant L'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntíty est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<User> response= restTemplate.exchange(
        getUserByPseudoUrl, // L'URL
        HttpMethod.GET, // La méthode HTTP
        null, // La requestEntity qui peut renvoyer un Corps+Header ou rien
        User.class // Le type de retour ici User.class car c'est un objet simple
    );

    log.debug("Get User by Pseudo" + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode createUser
   *
   *<i>de UserRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie le User une fois créé dans la BDD</p>
   * @param user Le User à créer
   * @return Le User créé
   */
  public User createUser(User user) {
    String BASE_API_URL = props.getApiUrl();
    String createUserUrl = BASE_API_URL + "/user";

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // HttpEntity est un objet qui retourne un Body et Headers
    HttpEntity<User> request = new HttpEntity<>(user);
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<User> response = restTemplate.exchange(
        createUserUrl, // L'URL
        HttpMethod.POST, // La méthode HTTP
        request, // Le HttpEntity
        User.class // Le type de retour ici User.class car c'est un objet simple
    );

    log.debug("Create User " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode updateUser
   *
   *<i>de UserRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie à la BDD un User à mettre à jour et renvoie le User</p>
   * @param user Le User mis à jour
   * @return Le User mis à jour si réussi
   */
  public User updateUser(User user) {
    String BASE_API_URL = props.getApiUrl();
    String updateUserUrl = BASE_API_URL + "/user/" + user.getUserId();

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // HttpEntity est un objet qui retourne un Body et Headers
    HttpEntity<User> request = new HttpEntity<>(user);
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<User> response = restTemplate.exchange(
        updateUserUrl, // L'URL
        HttpMethod.PUT, // La méthode HTTP
        request, // Le HttpEntity
        User.class // Le type de retour ici User.class car c'est un objet simple
    );

    log.debug("Update User " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode deleteUser
   *
   *<i>de UserRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Supprime le User avec l'id spécifié, ne renvoie rien</p>
   * @param id L'id du User à supprimer
   */
  public void deleteUser(int id) {
    String BASE_API_URL = props.getApiUrl();
    String deleteUserUrl = BASE_API_URL + "/user/" + id;

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Post> response = restTemplate.exchange(
        deleteUserUrl, // L'URL
        HttpMethod.DELETE, // La méthode HTTP
        null, // La requestEntity qui peut renvoyer un Corps+Header ou rien
        Post.class // Le type de retour ici Post.class car c'est un objet simple
    );

    log.debug("Delete User " + response.getStatusCode());
  }

}
