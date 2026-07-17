package fr.swif.codecase_web.repository;

import fr.swif.codecase_web.config.CustomProperties;
import fr.swif.codecase_web.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * AuthRepository
 * <i>de fr.swif.codecase_web.repository</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 16/07/2026
 */

// @Slf4j permet de générer un champ de log
@Slf4j
// @Component est une annotation Spring qui marque une classe comme bean géré
// par le conteneur IoC (Inversion of Control) de Spring
@Component
// @RequiredArgsConstructor génère automatiquement un constructeur prenant en
// paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class AuthRepository {

  private final CustomProperties props;

  /**
   * Méthode connecter
   *
   *<i>de AuthRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Appelle l'API pour connecter un User et renvoie la réponse complète
   * (contenant le header Set-Cookie du JWT)</p>
   * @param user L'email et le mot de passe saisis dans le formulaire
   * @return La réponse HTTP de l'API
   */
  public ResponseEntity<Void> connecter(User user) {
    String BASE_API_URL = props.getApiUrl();
    String connexionUrl = BASE_API_URL + "/user/authentification/connexion";

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<User> request = new HttpEntity<>(user);

    ResponseEntity<Void> response = restTemplate.exchange(
        connexionUrl, // L'URL
        HttpMethod.POST, // La méthode HTTP
        request, // Le HttpEntity contenant le User
        Void.class // Le type de retour, ici rien (le body API est vide)
    );

    log.debug("Connexion " + response.getStatusCode());

    return response;
  }

  /**
   * Méthode inscrire
   *
   *<i>de AuthRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Appelle l'API pour inscrire un User et renvoie la réponse complète
   * (contenant le header Set-Cookie du JWT)</p>
   * @param user Le User à créer, saisi dans le formulaire
   * @return La réponse HTTP de l'API
   */
  public ResponseEntity<Void> inscrire(User user) {
    String BASE_API_URL = props.getApiUrl();
    String inscriptionUrl = BASE_API_URL + "/user/authentification/inscription";

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<User> request = new HttpEntity<>(user);

    ResponseEntity<Void> response = restTemplate.exchange(
        inscriptionUrl, // L'URL
        HttpMethod.POST, // La méthode HTTP
        request, // Le HttpEntity contenant le User
        Void.class // Le type de retour, ici rien (le body API est vide)
    );

    log.debug("Inscription " + response.getStatusCode());

    return response;
  }

  /**
   * Méthode deconnecter
   *
   *<i>de AuthRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Appelle l'API pour invalider le cookie JWT côté serveur</p>
   */
  public void deconnecter() {
    String BASE_API_URL = props.getApiUrl();
    String deconnexionUrl = BASE_API_URL + "/user/authentification/deconnexion";

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<Void> response = restTemplate.exchange(
        deconnexionUrl, // L'URL
        HttpMethod.POST, // La méthode HTTP
        null, // La requestEntity qui peut renvoyer un Corps+Header ou rien
        Void.class // Le type de retour, ici rien (le body API est vide)
    );

    log.debug("Deconnexion " + response.getStatusCode());
  }
}
