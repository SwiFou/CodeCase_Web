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
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate userDateCreationCompte;

  /**
   * Variable userDerniereConnexion
   */
  @NotNull
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

  /**
   * Méthode setMdp (char[])
   *<i>de User</i>
   *<hr>
   *<p>Setter pour le mot de passe si venu d'un input, non hash</p>
   *
   * @param paraMdp venu d'un input
   */
  public void setMdp(char[] paraMdp){
    this.userMdp = paraMdp.toString();
  }

  /**
   * Méthode setMdp (String)
   *<i>de User</i>
   *<hr>
   *<p>Setter pour le mot de passe si venu d'une BDD, hashé</p>
   *
   * @param paraMdp venu d'une BDD
   */
  public void setMdp(String paraMdp){
    this.userMdp = paraMdp;
  }

  /**
   * Méthode setRole
   *<i>de User</i>
   *<hr>
   *<p>Prends le String venu de la BDD et en fait un Role</p>
   *
   * @param paraRole Le rôle sous forme de String
   */
  public void setRole(String paraRole){
    if (paraRole.equals("Admin")){
      this.userRole = Role.ADMIN;
    }else{
      this.userRole = Role.MEMBRE;
    }
  }

  @Override
  public String toString() {
    return userPseudo;
  }
}