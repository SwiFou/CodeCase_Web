package fr.swif.codecase_web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;

/**
 * Commentaire
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */

@Data
public class Commentaire {
  /**
   * Variable commentaireId
   */
  private Integer commentaireId;

  /**
   * Variable commentaireDate
   */
  private LocalDate commentaireDate;

  /**
   * Variable commentaireContenu
   */
  @NotBlank(message = "Vous devez saisir un commentaire pour qu'il puisse être valide")
  @Size(max = 600, message = "Le message ne doit pas dépasser les 600 caractères")
  private String commentaireContenu;
}
