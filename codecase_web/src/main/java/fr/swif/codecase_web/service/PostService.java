package fr.swif.codecase_web.service;

import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * PostService
 * <i>de fr.swif.codecase_web.service</i>
 * <hr>
 * <p></p>
 *
 * @author Calderoli Alexandre
 * @version 0.0.1
 * @since 22/06/2026
 */

@Service

@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public Iterable<Post> getPosts() {
    return postRepository.getPosts();
  }

  public Post getPost(int id) {
    return postRepository.getPost(id);
  }

  public void deletePost(int id) {
    postRepository.deletePost(id);
  }

  public Post createPost(Post post) {
    return postRepository.createPost(post);
  }

}
