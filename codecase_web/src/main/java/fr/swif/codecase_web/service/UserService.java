package fr.swif.codecase_web.service;

import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.model.User;
import fr.swif.codecase_web.repository.PostRepository;
import fr.swif.codecase_web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

/**
 * UserService
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
// @Service sert à indiquer que la classe détient la logique métier du CRUD
@Service
// @RequiredArgsConstructor génère automatiquement un constructeur prenant en
// paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  /**
   * Méthode getUsers
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie tous les Users en BDD</p>
   * @return Un Iterable de User
   */
  public Iterable<User> getUsers() {
    try {
      return userRepository.getUsers();
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  /**
   * Méthode getUser
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie un User avec l'id spécifié</p>
   * @param id L'id du User cherché
   * @return Le User
   */
  public User getUser(int id) {
    try {
      return userRepository.getUser(id);
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  /**
   * Méthode getUserByPseudo
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie un User avec le pseudo spécifié</p>
   * @param pseudo Le pseudo du User Cherché
   * @return Le User
   */
  public User getUserByPseudo(String pseudo) {
    try {
      return userRepository.getUserByPseudo(pseudo);
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  /**
   * Méthode deleteUser
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Supprime le User avec l'id spécifié, ne renvoie rien</p>
   * @param id L'id du User à supprimer
   */
  public void deleteUser(int id) {
    try {
      userRepository.deleteUser(id);
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  /**
   * Méthode createUser
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie le User une fois créé dans la BDD</p>
   * @param user Le User à créer
   * @return Le User créé
   */
  public User createUser(User user) {
    try {
      return userRepository.createUser(user);
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  /**
   * Méthode updateUser
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie à la BDD un User à mettre à jour et renvoie le User</p>
   * @param user Le User mis à jour
   * @return Le User mis à jour si réussi
   */
  public User updateUser(User user) {
    try {
      return userRepository.updateUser(user);
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  /**
   * Méthode anonymisationUser
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Anonymise le User avec l'id spécifié, ne renvoie rien </p>
   * @param id L'id du User à anonymiser
   */
  public void anonymisationUser(int id) {
    try {
      userRepository.anonymisationUser(id);
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

}
