package fr.swif.codecase_web.repository;

import fr.swif.codecase_web.config.CustomProperties;
import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.model.Technologie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * TechnologieRepository
 * <i>de fr.swif.codecase_web.repository</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 02/07/2026
 */

// @Slf4j permet de générer un champ de log
@Slf4j
// @Service sert à indiquer que la classe détient la logique métier
@Service
// @RequiredArgsConstructor génère automatiquement un constructeur prenant en
// paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class TechnologieRepository {

  private final CustomProperties props;

  /**
   * Méthode getTechnologies
   *
   *<i>de TechnologieRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie toutes les Technologies en BDD</p>
   * @return un Iterable de Technologie
   */
  public Iterable<Technologie> getTechnologies() {
    String BASE_API_URL = props.getApiUrl();
    String getTechnologiesUrl = BASE_API_URL + "/technologies";

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Iterable<Technologie>> response = restTemplate.exchange(
        getTechnologiesUrl, // L'URL
        HttpMethod.GET, // La méthode HTTP
        null, // La requestEntity qui peut renvoyer un Corps+Header ou rien
        new ParameterizedTypeReference<>() {} // Le type de retour ici ParameterizedTypeReference car c'est un Iterable
    );
    log.debug("Get Technologies " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode getTechnologie
   *
   *<i>de TechnologieRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie une Technologie avec l'id spécifié</p>
   * @param id L'id de la Technologie cherchée
   * @return La Technologie
   */
  public Technologie getTechnologie(int id) {
    String BASE_API_URL = props.getApiUrl();
    String getTechnologieUrl = BASE_API_URL + "/technologie/" + id;

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Technologie> response = restTemplate.exchange(
        getTechnologieUrl, // L'URL
        HttpMethod.GET, // La méthode HTTP
        null, // La requestEntity qui peut renvoyer un Corps+Header ou rien
        Technologie.class // Le type de retour ici Technologie.class car c'est un objet simple
    );

    log.debug("Get Technologie " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

  /**
   * Méthode createTechnologie
   *
   *<i>de TechnologieRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie la Technologie une fois créé dans la BDD</p>
   * @param technologie La Technologie à créer
   * @return La Technologie créée
   */
  public Technologie createTechnologie(Technologie technologie) {
    String BASE_API_URL = props.getApiUrl();
    String createTechnologieUrl = BASE_API_URL + "/technologie";

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // HttpEntity est un objet qui retourne un Body et Headers
    HttpEntity<Technologie> request = new HttpEntity<>(technologie);
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Technologie> response = restTemplate.exchange(
        createTechnologieUrl, // L'URL
        HttpMethod.POST, // La méthode HTTP
        request, // Le HttpEntity
        Technologie.class // Le type de retour ici Technologie.class car c'est un objet simple
    );

    log.debug("Create Technologie " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

}
