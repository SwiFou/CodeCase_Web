package fr.swif.codecase_web.config;

import fr.swif.codecase_web.filter.JwtWebFiltre;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfigWeb
 * <i>de fr.swif.codecase_web.config</i>
 * <hr>
 * <p>Cette classe permet de configurer Spring Security pour le module web
 * (Thymeleaf sans Javascript).
 * Ici contrairement à l'API, on ne renvoie pas de code erreur 401/403, mais on
 * redirige vers la page de connexion.</p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 15/07/2026
 */

// @Configutration indique qu'une classe déclare une ou plusieurs méthodes
// annotées par @Bean
@Configuration
// @EnableWebSecuity sert à activer la configuration Spring Security Web.
// Sans cette annotation, le @Bean securityFilterChain ne serait jamais
// réellement "branché" sur les requêtes HTTP entrantes
@EnableWebSecurity
// @RequiredArgsConstructor génère automatiquement un constructeur prenant
// en paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
public class SecurityConfigWeb {

  private final JwtWebFiltre jwtWebFiltre;

  // @Bean sert à déclarer manuellement un bean Spring (c'est un objet géré par
  // le conteneur Ioc)
  @Bean
  public SecurityFilterChain filterChainWeb(HttpSecurity http) throws Exception {
    http
        // Pas de session HTTP : l'état d'authentification vient uniquement
        // du cookie JWT
        .sessionManagement(
            session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // Pas de formLogin Spring classique : on gère nous-mêmes /connexion
        // via AuthWebController
        .formLogin(form -> form.disable())
        //! à réactiver si tu ajoutes un token CSRF dans tes formulaires Thymeleaf
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/",
                "/accueil",
                "/connexion",
                "/inscription",
                "/css/**",
                "/images/**",
                "/webjars/**"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .exceptionHandling(
            exception ->
                exception
            // Si non authentifié sur une route protégée → redirection vers
            // /connexion
            .authenticationEntryPoint(
                new LoginUrlAuthenticationEntryPoint("/connexion"))
        )
        // Notre filtre lit le cookie JWT et peuple le SecurityContext avant
        // le filtre standard
        .addFilterBefore(jwtWebFiltre, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}

