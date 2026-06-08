package fr.swif.codecase_web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Sanction
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */

@Data
public class Sanction {
  /**
   * Variable sanctionId
   */
  private Integer sanctionId;

  /**
   * Variable sanctionType
   */
  SanctionType sanctionType;

  /**
   * Variable sanctionStatut
   */
  SanctionStatut sanctionStatut;

  /**
   * Variable sanctionDate
   */
  @NotBlank(message = "Vous devez saisir la date de la sanction")
  private LocalDate sanctionDate;

  /**
   * Variable sanctionDateFin
   */
  @NotBlank(message = "Vous devez saisir la date de fin de la sanction")
  private LocalDateTime sanctionDateFin;

  /**
   * Variable sanctionMotif
   */
  @NotBlank(message = "Vous devez saisir un motif pour que la sanction soit effective")
  @Size(max = 100, message = "Le motif ne doit pas dépasser les 100 caractères")
  private String sanctionMotif;

}
