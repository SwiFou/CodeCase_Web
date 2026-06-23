package fr.swif.codecase_web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CustomProperties
 * <i>de fr.swif.codecase_web.config</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 18/06/2026
 */

// @Data est l'équivalent de @Getter @Setter @RequiredArgsConstructor
// @ToString @EqualsAndHashCode
@Data
// @ConfigurationProperties permet de lier et de valider des propriétés
// externes (par exemple, provenant d'un fichier .properties)
@ConfigurationProperties(prefix = "fr.swif.codecase-web.appweb")
public class CustomProperties {

  /**
   * L'URL vers l'API
   */
  private String apiUrl;
}
