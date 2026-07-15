package fr.swif.codecase_web.service;

import fr.swif.codecase_web.exception.CodeCaseWebException;
import fr.swif.codecase_web.model.User;
import fr.swif.codecase_web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

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
// @Service sert à indiquer que la classe détient la logique métier
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
   * @throws CodeCaseWebException
   */
  public Iterable<User> getUsers() throws CodeCaseWebException {
    try {
      return userRepository.getUsers();
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Impossible de "
          + "récupérer la liste des utilisateurs.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors du chargement des utilisateurs. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
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
   * @return Le User cherché
   * @throws CodeCaseWebException
   */
  public User getUser(int id) throws CodeCaseWebException{
    try {
      return userRepository.getUser(id);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "L'utilisateur "
          + "demandé n'existe pas ou n'est plus disponible.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la récupération de l'utilisateur. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
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
   * @return Le User cherché
   * @throws CodeCaseWebException
   */
  public User getUserByPseudo(String pseudo) throws CodeCaseWebException{
    try {
      return userRepository.getUserByPseudo(pseudo);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Aucun utilisateur "
          + "trouvé avec ce pseudo.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la recherche de l'utilisateur. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

  /**
   * Méthode getUserByMail
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie un User avec le mail spécifié</p>
   * @param mail Le mail du User cherché
   * @return Le User cherché
   * @throws CodeCaseWebException
   */
  public User getUserByMail(String mail) throws CodeCaseWebException{
    try {
      return userRepository.getUserByMail(mail);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Aucun utilisateur "
          + "trouvé avec cette adresse e-mail.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la recherche de l'utilisateur. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
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
   * @throws CodeCaseWebException
   */
  public void deleteUser(int id) throws CodeCaseWebException{
    try {
      userRepository.deleteUser(id);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Ce compte n'existe "
          + "pas ou a déjà été supprimé.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la suppression du compte. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
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
   * @throws CodeCaseWebException
   */
  public User createUser(User user) throws CodeCaseWebException{
    try {
      return userRepository.createUser(user);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Votre inscription "
          + "n'a pas pu être finalisée. "
          + "Merci de vérifier les informations saisies.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la création de votre compte. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
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
   * @return Le User mis à jour
   * @throws CodeCaseWebException
   */
  public User updateUser(User user) throws CodeCaseWebException{
    try {
      return userRepository.updateUser(user);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Vos informations "
          + "n'ont pas pu être mises à jour. "
          + "Merci de vérifier les données saisies.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la mise à jour de votre profil. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

  /**
   * Méthode anonymisationUser
   *
   *<i>de UserService</i>
   *<h1></h1>
   *<hr>
   *<p>Anonymise le User avec l'id spécifié, ne renvoie rien</p>
   * @param id L'id du User à anonymiser
   * @throws CodeCaseWebException
   */
  public void anonymisationUser(int id) throws CodeCaseWebException{
    try {
      userRepository.anonymisationUser(id);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Impossible "
          + "d'anonymiser ce compte utilisateur.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de l'anonymisation du compte. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

}
