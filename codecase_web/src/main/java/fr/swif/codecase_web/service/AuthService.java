package fr.swif.codecase_web.service;

import fr.swif.codecase_web.exception.CodeCaseWebException;
import fr.swif.codecase_web.model.User;
import fr.swif.codecase_web.repository.AuthRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

/**
 * AuthService
 * <i>de fr.swif.codecase_web.service</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 16/07/2026
 */

// @Slf4j permet de générer un champ de log
@Slf4j
// @Service sert à indiquer que la classe détient la logique métier
@Service
// @RequiredArgsConstructor génère automatiquement un constructeur prenant en
// paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class AuthService {

  private final AuthRepository authRepository;

  public void connecterUser(User user, HttpServletResponse response)
      throws CodeCaseWebException {
    try {
      ResponseEntity<Void> apiResponse = authRepository.connecter(user);
      relayerCookieJwt(apiResponse, response);
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      throw new CodeCaseWebException(cx.getStatusCode(), "Email ou mot de "
          + "passe incorrect.");
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de la connexion. Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

  public void inscrireUser(User user, HttpServletResponse response)
      throws CodeCaseWebException {
    try {
      ResponseEntity<Void> apiResponse = authRepository.inscrire(user);
      relayerCookieJwt(apiResponse, response);
    } catch (HttpClientErrorException cx) {
      log.error("Erreur API : {} - {}", cx.getStatusCode(), cx.getMessage());
      String message = cx.getStatusCode() == HttpStatus.CONFLICT
          ? "Cet email est déjà utilisé."
          : "Inscription impossible, vérifie les informations saisies.";
      throw new CodeCaseWebException(cx.getStatusCode(), message);
    } catch (HttpServerErrorException sx) {
      log.error("Erreur API : {} - {}", sx.getStatusCode(), sx.getMessage());
      throw new CodeCaseWebException(sx.getStatusCode(), "Une erreur est "
          + "survenue lors de l'inscription. Merci de réessayer plus tard.");
    } catch (ResourceAccessException ra) {
      log.error("Service API indisponible : {}", ra.getMessage());
      throw new CodeCaseWebException("Le service est temporairement "
          + "indisponible. Merci de réessayer plus tard.");
    }
  }

  public void deconnecterUser(HttpServletResponse response)
      throws CodeCaseWebException {
    try {
      authRepository.deconnecter();
    } catch (HttpClientErrorException | HttpServerErrorException |
             ResourceAccessException e) {
      log.warn("Échec d'appel à /deconnexion côté API, on nettoie quand même "
          + "le cookie local : {}", e.getMessage());
    }

    Cookie cookieExpire = new Cookie("jwt", null);
    cookieExpire.setPath("/");
    cookieExpire.setMaxAge(0);
    cookieExpire.setHttpOnly(true);
    response.addCookie(cookieExpire);
  }

  private void relayerCookieJwt(ResponseEntity<?> apiResponse,
      HttpServletResponse response) {
    List<String> cookiesApi = apiResponse.getHeaders().get(HttpHeaders.SET_COOKIE);
    if (cookiesApi != null) {
      for (String cookie : cookiesApi) {
        response.addHeader(HttpHeaders.SET_COOKIE, cookie);
      }
    }
  }
}
