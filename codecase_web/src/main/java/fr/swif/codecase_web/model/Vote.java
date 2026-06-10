package fr.swif.codecase_web.model;

import lombok.Data;

/**
 * Vote
 * <i>de fr.swif.codecase_web.model</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 08/06/2026
 */

@Data
public class Vote {
  /**
   * Variable voteId
   */
  private Integer voteId;

  /**
   * Variable voteType
   */
  private VoteType voteType;

}
