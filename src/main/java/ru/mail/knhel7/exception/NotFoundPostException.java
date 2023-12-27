package ru.mail.knhel7.exception;

public class NotFoundPostException extends RuntimeException {
  public NotFoundPostException() {
  }

  public NotFoundPostException(String message) {
    super(message);
  }

  public NotFoundPostException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundPostException(Throwable cause) {
    super(cause);
  }

  public NotFoundPostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
