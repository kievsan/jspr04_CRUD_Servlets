package ru.mail.knhel7.model;

public class Post {
  private final long ID;
  private String content;

  public Post(long id, String content) {
    this.ID = id;
    this.content = content;
  }

  public long getID() {
    return ID;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
