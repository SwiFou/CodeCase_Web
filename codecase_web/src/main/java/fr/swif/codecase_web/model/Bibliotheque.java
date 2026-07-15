package fr.swif.codecase_web.model;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Bibliothèque
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */

@Data
public class Bibliotheque {

  /**
   * Variable bibliothequeId
   */
  private Integer bibliothequeId;

  /**
   * Variable bibliothequeLibelle
   */
  @Size(min = 3, max = 20, message = "Le nom de la bibliothèque doit être de"
      + " minimum 3 caractères et de maximum 20 caractères")
  private String bibliothequeLibelle;
}
