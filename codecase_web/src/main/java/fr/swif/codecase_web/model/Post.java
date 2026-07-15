package fr.swif.codecase_web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Post
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */
@Data
public class Post {

  /**
   * Variable postId
   */
  private Integer postId;

  /**
   * Variable postTitre
   */
  @Size(min = 3, max = 100,
      message = "Le titre doit être renseigné et"
          + " être entre 3 et 100 caractères")
  private String postTitre;

  /**
   * Variable postDescription
   */
  @NotBlank(message = "La description ne doit pas être vide")
  @Size(max = 250,
      message = "La description doit contenir au maximum 250 caractères")
  private String postDescription;

  /**
   * Variable postContenu
   */
  @NotBlank(message = "Le post ne doit pas être vide")
  @Size(max = 5000,
      message = "Le contenu du post doit être de maximum 5000 caractères")
  private String postContenu;

  /**
   * Variable userId
   */
  private User userId;

  /**
   * Variable langageId de type Langage
   */
  private Langage langageId;

  /**
   * Variable technologieId de type Technologie
   */
  private Technologie technologieId;

  /**
   * Variable creationDatePost
   */
  private LocalDateTime postDateCreation;

}
