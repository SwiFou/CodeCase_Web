package fr.swif.codecase_web.service;

import fr.swif.codecase_web.model.Post;
import fr.swif.codecase_web.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

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
    try {
      return postRepository.getPosts();
    } catch (HttpClientErrorException cx) {
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  public Post getPost(int id) {
    try {
      return postRepository.getPost(id);
    } catch (HttpClientErrorException cx) {
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  public void deletePost(int id) {
    try {
      postRepository.deletePost(id);
    } catch (HttpClientErrorException cx) {
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

  public Post createPost(Post post) {
    try {
      return postRepository.createPost(post);
    } catch (HttpClientErrorException cx) {
      throw new ResponseStatusException(cx.getStatusCode(), "API inaccessible");
    } catch (HttpServerErrorException sx) {
      throw new ResponseStatusException(sx.getStatusCode(), "Erreur serveur API");
    }
  }

}
