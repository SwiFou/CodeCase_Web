package fr.swif.codecase_web.repository;

import fr.swif.codecase_web.config.CustomProperties;
import fr.swif.codecase_web.model.Langage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * LangageRepository
 * <i>de fr.swif.codecase_web.repository</i>
 * <hr>
 * <p>Repository qui fait le lien entre l'api et la webapp de Langage</p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 02/07/2026
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
public class LangageRepository {

  private final CustomProperties props;

  /**
   * Méthode getLangages
   *
   *<i>de LangageRepository</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie tous les Langages en BDD</p>
   * @return Un Iterable de Langage
   */
  public Iterable<Langage> getLangages() {
    String BASE_API_URL = props.getApiUrl();
    String getLangagesUrl = BASE_API_URL + "/langages";

    // RestTemplate permet d'exécuter une requête HTTP, en fournissant l'URL, le
    // type de requête (GET, POST, etc.) et le type d'objet qui sera retourné.
    // Il fait la requête à l'API et convertit le résultat JSON en objet Java.
    RestTemplate restTemplate = new RestTemplate();
    // ResponseEntity est une classe Spring qui représente toute la réponse HTTP
    // exchange permet de transmettre :
    ResponseEntity<Iterable<Langage>> response = restTemplate.exchange(
        getLangagesUrl, // L'URL
        HttpMethod.GET, // La méthode HTTP
        null, // La requestEntity qui peut renvoyer un Corps+Header ou rien
        new ParameterizedTypeReference<>() {} // Le type de retour ici ParameterizedTypeReference car c'est un Iterable
    );
    log.debug("Get Langages " + response.getStatusCode());

    // On récupère l'objet grâce à la méthode getBody() de l'objet Response
    return response.getBody();
  }

}
