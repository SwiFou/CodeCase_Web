package fr.swif.codecase_web.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
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
  @Size(max = 250,
      message = "La description doit contenir au maximum 250 caractères")
  private String postDescription;

  /**
   * Variable postContenu
   */
  @NotEmpty(message = "Le post ne doit pas être vide")
  private String postContenu;

  /**
   * Variable userId
   */
  private User userId;

  /**
   * Variable langageId de type langage
   */
  private Langage langageId;

  /**
   * Variable tagCustom, dans une Arraylist de type Tag
   */
//  Set<Tag> tagCustom;

  /**
   * Variable creationDatePost
   */
  private LocalDate creationDatePost;

}
