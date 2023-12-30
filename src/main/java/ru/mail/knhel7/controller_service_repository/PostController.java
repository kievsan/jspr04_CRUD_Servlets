package ru.mail.knhel7.controller_service_repository;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import org.springframework.stereotype.Controller;
import ru.mail.knhel7.exception.NotFoundPostException;
import ru.mail.knhel7.model.Post;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

import static ru.mail.knhel7.Const.*;


@Controller
public class PostController {
  private final PostService service;
  private final Gson gson;

  public PostController(PostService service) {
    this.service = service;
    this.gson = new Gson();
  }

  public void all(HttpServletResponse response) {
    try {
      response.setContentType(APP_JSON);
      final String jsonPosts = gson.toJson(service.all());
      response.getWriter().write(jsonPosts);
      response.setStatus(HttpServletResponse.SC_OK);

    } catch (IOException e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  public void getById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
    try {
      response.setContentType(APP_JSON);
      final String postJson = gson.toJson(service.getById(id));
      response.getWriter().write(postJson);
      response.setStatus(HttpServletResponse.SC_OK);

    } catch (NotFoundPostException e) {
      try {
        response.getWriter().write("The post was not found");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } catch (IOException e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  public void save(Reader body, HttpServletResponse response
  ) throws IOException, JsonIOException {
    response.setContentType(APP_JSON);
    final var post = gson.fromJson(body, Post.class);
    response.getWriter().print(gson.toJson(service.save(post)));
    response.setStatus(HttpServletResponse.SC_OK);
  }

  public void removeById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
    final var deleted = service.removeById(id);
    if (deleted.isEmpty()) {
      try {
        response.getWriter().write("The post was not found");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      return;
    }
    try {
      response.setContentType(APP_JSON);
      response.getWriter().write(gson.toJson(deleted.get()));
      response.setStatus(HttpServletResponse.SC_OK);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
