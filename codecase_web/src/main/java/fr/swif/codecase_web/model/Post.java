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
   * Variable id.
   */
  private Integer postId;

  /**
   * Variable titre.
   */
  @Size(min = 3, max = 100,
      message = "Le titre doit être renseigné et"
          + " être entre 3 et 100 caractères")
  private String postTitre;

  /**
   * Variable description.
   */
  @Size(max = 250,
      message = "La description doit contenir au maximum 250 caractères")
  private String postDescription;

  /**
   * Variable contenu.
   */
  @NotEmpty(message = "Le post ne doit pas être vide")
  private String postContenu;

  /**
   * Variable userId.
   */
  private User userId;

  /**
   * Variable language de type Tag.
   */
  private Tag langageId;

  /**
   * Variable tagCustom, dans une Arraylist de type Tag.
   */
//  Set<Tag> tagCustom;

  /**
   * Variable creationPost.
   */
  private LocalDate creationDatePost;

}
