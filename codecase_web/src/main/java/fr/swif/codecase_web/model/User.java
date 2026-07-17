package fr.swif.codecase_web.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * User
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */

// @Data est l'équivalent de @Getter @Setter @RequiredArgsConstructor
// @ToString @EqualsAndHashCode
@Data
public class User {

  /**
   * Variable userId
   */
  private Integer userId;

  /**
   * Variable userPseudo
   */
  @Size(min = 3, max = 30, message = "Le pseudonyme doit être de 3 à 30 " +
      "caractères")
  private String userPseudo;

  /**
   * Variable userMdp
   */
  private String userMdp;

  /**
   * Variable userEmail
   */
  @Email
  private String userEmail;

  /**
   * Variable role de type Role
   */
  @NotNull
  private Role userRole;

  /**
   * Variable userDateCreationCompte
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate userDateCreationCompte;

  /**
   * Variable userDerniereConnexion
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime userDerniereConnexion;

  /**
   * Variable userAvatar
   */
  @NotBlank(message = "Vous devez choisir une image")
  @Size(max = 250, message =
      "Le nom de l'image ne doit pas dépasser 250 caractères")
  private String userAvatar;

  /**
   * Variable userMfaActif
   */
  private boolean userMfaActif;

  @Override
  public String toString() {
    return userPseudo;
  }
}