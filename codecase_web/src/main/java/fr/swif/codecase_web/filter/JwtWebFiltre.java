package fr.swif.codecase_web.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


/**
 * JwtWebFiltre
 * <i>de fr.swif.codecase_web.filter</i>
 * <hr>
 * <p>Cette classe permet de lire le cookie "jwt" créé par l'API et s'il est
 * valide → authentifie l'utilisateur côté web (SecurityContext) pour que
 * Spring Security et les templates Thymeleaf (sec:authorize) sachent qu'il est
 * connecté.
 *
 * OncePerRequestFilter précise que ce filtre Spring Security s'exécute 1 fois
 * par requête</p>
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
// OncePerRequestFilter précise que ce filtre Spring Security s'exécute 1 fois
// par requête
public class JwtWebFiltre extends OncePerRequestFilter {

  // @Value permet d'injecter une valeur qui est définie dans
  // application.properties
  @Value("${app.secret-key}")
  private String secretKey;

  /**
   * Constante qui centralise le nom du cookie. Cela évite de répéter la
   * chaîne en dur
   */
  private static final String NOM_COOKIE_JWT = "jwt";

  /**
   * Méthode doFilterInternal
   * @param request
   * @param response
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  // @Override sert à redéfinir une méthode héritée
  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // Récupération de la valeur du cookie JWT depuis la requête
    String token = extractionJwtDuCookie(request);

    if (token != null) {
      try {
        // On décode et vérifie la signature du token
        // claims prend les informations encodées dans la JWT (subject, rôle,
        // date d'expiration, etc.)
        Claims claims = extractAllClaims(token);

        // On récupère le subject du token. Ici l'adresse mail de l'utilisateur
        String email = claims.getSubject();
        // On récupère un claim du token. Ici le rôle de l'utilisateur
        String role = claims.get("role", String.class);

        // GrantedAuthority est une interface Spring Security qui représente une
        // permission/un rôle accordé à un utilisateur authentifié
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(role != null) {
          authorities.add(new SimpleGrantedAuthority(role));
        }

        // Construction de l'objet d'authentification Spring Security à partir
        // de l'utilisateur et de ses rôles
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                // L'identité du User (Ici le mail extrait du subject de la JWT)
                email,
                // Les credentials (normalement le mot de passe).
                // Ici null car le mot de passe n'est plus nécessaire :
                // l'utilisateur a déjà prouvé son identité via son token
                // JWT valide, donc pas besoin de re-vérifier un mot de passe.
                null,
                // La liste des rôles/permission que l'utilisateur a
                authorities
            );

        // Enregistre l'utilisateur comme étant authentifié dans le contexte de
        // sécurité de la requête courante (utilisé ensuite pour les
        // vérifications d'autorisation)
        SecurityContextHolder.getContext().setAuthentication(authentication);

      } catch (ExpiredJwtException eje) {
        log.debug("Tentative d'authentification avec un token JWT expiré côté"
            + "web : {}", eje.getMessage());
        SecurityContextHolder.clearContext();
      } catch (JwtException | IllegalArgumentException e) {
        log.warn("Tentative d'authentification avec un token JWT invalide côté"
            + "web : {}", e.getMessage());
        SecurityContextHolder.clearContext();
      }
    }

    // Poursuit l'exécution de la chaîne de filtres (obligatoire, sinon la
    // requête reste bloquée ici) : ce filtre ne fait qu'authentifier,
    // il ne bloque jamais
    filterChain.doFilter(request, response);
  }

  /**
   * Méthode extraireTokenDuCookie
   *
   *<i>de JwtWebFiltre</i>
   *<h1></h1>
   *<hr>
   *<p>Cette méthode permet d'extraire la valeur du token JWT depuis les cookies
   * de la requête entrante</p>
   * @param request La requête HTTP entrante
   * @return Le token JWT s'il est présent dans les cookies, sinon null
   */
  private String extractionJwtDuCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

    if(cookies == null) {
      return null;
    }

    for(Cookie cookie : cookies) {
      if(NOM_COOKIE_JWT.equals(cookie.getName())) {
        return cookie.getValue();
      }
    }
    //!
    return null;
  }

  /**
   * Méthode extractAllClaims
   *
   *<i>de JwtWebFiltre</i>
   *<h1></h1>
   *<hr>
   *<p>Cette méthode permet de parser le token JWT et de vérifier sa signature
   * à l'aide de la clé secrète.
   * Lève une JwtException si le token est invalide, expiré ou mal signé</p>
   * @param token Le token JWT à parser
   * @return Les claims extraits du token
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSignatureKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  /**
   * Méthode getSignatureKey
   *
   *<i>de JwtWebFiltre</i>
   *<h1></h1>
   *<hr>
   *<p>Cette méthode permet de construire la clé de signature HMAC-SHA à partir
   * de la clé secrète configurée dans application.properties, encodée en UTF-8.
   * Cette clé doit être identique à celle utilisée côté codecase_api pour que
   * la vérification de signature fonctionne.</p>
   * @return La clé secrète utilisée pour vérifier les token
   */
  private SecretKey getSignatureKey() {
    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }


}
