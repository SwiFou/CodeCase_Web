package fr.swif.codecase_web.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;


/**
 * IntercepteurCookie
 * <i>de fr.swif.codecase_web.config</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 15/07/2026
 */

// @Slf4j permet de générer un champ de log
@Slf4j
// @Component est une annotation Spring qui marque une classe comme bean géré
// par le conteneur IoC (Inversion of Control) de Spring
// Spring scan tous les packages et instancie automatiquement toutes les classes
// annotées @Component
@Component
public class IntercepteurCookie implements ClientHttpRequestInterceptor {

  private static final String NOM_COOKIE_JWT = "jwt";

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {

    String jwt = recupererJwtDeLaRequeteEntrante();

    if (jwt != null) {
      request.getHeaders().add(
          "Cookie",
          NOM_COOKIE_JWT + "=" + jwt);
    }

    return execution.execute(request, body);
  }

  private String recupererJwtDeLaRequeteEntrante() {
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    if (attributes == null) {
      return null; // pas de requête HTTP en cours (ex: appel depuis un batch/CLI)
    }

    HttpServletRequest currentRequest = attributes.getRequest();
    if (currentRequest.getCookies() == null) {
      return null;
    }

    for (Cookie cookie : currentRequest.getCookies()) {
      if (NOM_COOKIE_JWT.equals(cookie.getName())) {
        return cookie.getValue();
      }
    }
    return null;
  }


}
