package fr.swif.codecase_web.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
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
   * Variable id
   */
  private Integer userId;

  /**
   * Variable pseudo
   */
  @Size(min = 3, max = 20, message = "Le pseudonyme doit être de 3 à 20 " +
      "caractères")
  private String userPseudo;

  /**
   * Variable mdp
   */
  private String userMdp;

  /**
   * Variable email
   */
  @Email
  private String userEmail;

  /**
   * Variable role de type Role (Classe à créer)
   */
  Role role;

  /**
   * Variable signInDate
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate userDateCreationCompte;

  /**
   * Variable lastLogin
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate userDerniereConnexion;

  /**
   * Variable avatarUser
   */
  @Max(250)
  private String userAvatar;

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
      this.role = Role.ADMIN;
    }else{
      this.role = Role.USER;
    }
  }

  @Override
  public String toString() {
    return userPseudo;
  }
}