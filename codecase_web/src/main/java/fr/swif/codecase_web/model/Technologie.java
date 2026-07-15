package fr.swif.codecase_web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Technologie
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */

@Data
public class Technologie {
  /**
   * Variable technologieId
   */
  private Integer technologieId;

  /**
   * Variable technologieIntitule
   */
//  @NotBlank(message = "Vous devez saisir un Outil et/ou une Technologie")
  private String technologieIntitule;

  /**
   * Variable userId de type User
   */
  private User userId;

}
