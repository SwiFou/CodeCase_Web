package fr.swif.codecase_web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;

/**
 * Signalement
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */

@Data
public class Signalement {
  /**
   * Variable signalementId
   */
  private Integer signalementId;

  /**
   * Variable signalementDate
   */
  @NotEmpty(message = "Vous devez saisir une date pour pouvoir valider le signalement")
  private LocalDate signalementDate;

  /**
   * Variable signalementDescription
   */
  @NotBlank(message = "Vous devez saisir une description pour pouvoir valider le signalement")
  @Size(max = 100, message = "La description ne doit pas dépasser les 100 caractères")
  private String signalementDescription;

}
