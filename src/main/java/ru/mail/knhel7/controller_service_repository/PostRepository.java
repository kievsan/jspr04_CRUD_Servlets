package ru.mail.knhel7.controller_service_repository;

import org.springframework.stereotype.Repository;
import ru.mail.knhel7.model.Post;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;


@Repository
public class PostRepository {

  private final AtomicLong ID = new AtomicLong(0);
  private final ConcurrentHashMap<Long, Post> posts;

  public PostRepository(){
    posts = new ConcurrentHashMap<>();
  }

  public ConcurrentHashMap<Long, Post> getPosts() {
    return posts;
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    return post.getID() == 0 ? newPost(post) : newPost(getById(post.getID()).orElse(newPost(post)));
  }

  private Post newPost(Post post){
    final var newPost = new Post(ID.incrementAndGet(), post.getContent());
    posts.put(newPost.getID(), newPost);
    return newPost;
  }

  public Optional<Post> removeById(long id) {
    return Optional.ofNullable(posts.remove(id));
  }
}
