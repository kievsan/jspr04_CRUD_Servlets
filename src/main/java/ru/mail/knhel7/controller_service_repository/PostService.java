package ru.mail.knhel7.controller_service_repository;

import ru.mail.knhel7.exception.NotFoundPostException;
import ru.mail.knhel7.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


public class PostService {

  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return new CopyOnWriteArrayList<>(repository.getPosts().values());
  }

  public Optional<Post> getById(long id) throws NotFoundPostException {
    return repository.getById(id);
//    return repository.getById(id).orElseThrow(NotFoundPostException::new);
  }

  public Post save(Post post) {
    return repository.save(post);
  }

  public Optional<Post> removeById(long id) {
    return repository.removeById(id);
//    return repository.removeById(id).orElseThrow(NotFoundPostException::new);
  }
}

