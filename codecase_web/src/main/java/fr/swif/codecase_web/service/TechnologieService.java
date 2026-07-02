package fr.swif.codecase_web.service;

import fr.swif.codecase_web.exception.CodeCaseWebException;
import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.model.Technologie;
import fr.swif.codecase_web.repository.TechnologieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

/**
 * TechnologieService
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
public class TechnologieService {

  private final TechnologieRepository technologieRepository;

  /**
   * Méthode getTechnologies
   *
   *<i>de TechnologieService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie toutes les Technologies en BDD</p>
   * @return Un Iterable de Technologie
   * @throws CodeCaseWebException
   */
  public Iterable<Technologie> getTechnologies() throws CodeCaseWebException {
    try {
      return technologieRepository.getTechnologies();
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Impossible de "
          + "récupérer la liste des technologies.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors du chargement des technologies. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

  /**
   * Méthode
   *
   *<i>de TechnologieService</i>
   *<h1></h1>
   *<hr>
   *<p>Renvoie la Technologie une fois créée dans la BDD</p>
   * @param technologie La Technologie à créer
   * @return La Technologie créée
   * @throws CodeCaseWebException
   */
  public Technologie createTechnologie(Technologie technologie) throws CodeCaseWebException{
    try {
      return technologieRepository.createTechnologie(technologie);
    } catch (HttpClientErrorException cx) {
      // Les {} sont des placeholders : le code de statut et le message vont
      // se placer dans les {}
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Votre demande n'a "
          + "pas pu être traitée. Merci de vérifier les informations saisies.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la publication de votre technologie. "
          + "Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Une erreur est survenue lors de la "
          + "publication de votre technologie.");
    }
  }

}
