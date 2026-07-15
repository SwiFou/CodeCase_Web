package fr.swif.codecase_web.model;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Langage
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */

@Data
public class Langage {
  /**
   * Variable langageId
   */
  private Integer langageId;

  /**
   * Variable langageIntitule
   */
  @Size(min = 3, max = 30, message =
      "Le tag doit avoir un minimum de 3 caractères et un maximum de 30")
  private String langageIntitule;

}
