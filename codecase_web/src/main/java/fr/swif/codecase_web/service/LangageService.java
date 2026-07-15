package fr.swif.codecase_web.service;

import fr.swif.codecase_web.exception.CodeCaseWebException;
import fr.swif.codecase_web.model.Langage;
import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.repository.LangageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

/**
 * LangageService
 * <i>de fr.swif.codecase_web.service</i>
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
public class LangageService {

  private final LangageRepository langageRepository;

  /**
   * Méthode getLangages
   *
   *<i>de LangageService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie tous les Langages en BDD</p>
   * @return Un Iterable de Langage
   * @throws CodeCaseWebException
   */
  public Iterable<Langage> getLangages() throws CodeCaseWebException {
    try {
      return langageRepository.getLangages();
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Impossible de "
          + "récupérer la liste des langages.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors du chargement des langages. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

  /**
   * Méthode getLangage
   *
   *<i>de LangageService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie un Langage avec l'id spécifié</p>
   * @param id L'id du Langage cherché
   * @return Le langage
   * @throws CodeCaseWebException
   */
  public Langage getLangage(int id) throws CodeCaseWebException {
    try {
      return langageRepository.getLangage(id);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Le langage demandé "
          + "n'existe pas ou n'est plus disponible.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors du chargement du langage. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

}
