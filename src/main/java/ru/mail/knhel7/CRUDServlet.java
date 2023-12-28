package ru.mail.knhel7;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mail.knhel7.controller_service_repository.PostController;
import static ru.mail.knhel7.Const.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CRUDServlet extends HttpServlet {
  private PostController controller;

  @Override
  public void init() {
    final var context = new AnnotationConfigApplicationContext("ru.mail.knhel7");
    controller = context.getBean(PostController.class);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    // если деплоились в root context, то достаточно этого
    try {
      final var path = req.getRequestURI();
      final var method = req.getMethod();

      if (method.equals(GET) && path.equals(PATH)) {
          controller.all(resp);
      } else if (method.equals(GET) && path.matches(PATH_ID)) {
          controller.getById(parseId(path), resp);
      } else if (method.equals(POST) && path.equals(PATH)) {
          controller.save(req.getReader(), resp);
      } else if (method.equals(DELETE) && path.matches(PATH_ID)) {
          controller.removeById(parseId(path), resp);
      } else {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      e.printStackTrace();
    }
  }

  private long parseId(String path) {
    return Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
  }

}
