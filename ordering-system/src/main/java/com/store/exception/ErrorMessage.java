package com.store.exception;

import java.util.Objects;

public class ErrorMessage {

  private int error;
  private String message;

  public ErrorMessage(int error, String message) {
    this.error = error;
    this.message = message;
  }

  public int getError() {
    return error;
  }

  public void setError(int error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, message);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ErrorMessage)) {
      return false;
    }
    ErrorMessage other = (ErrorMessage) obj;
    return Objects.equals(error, other.error)
      && Objects.equals(message, other.message);
  }

  @Override
  public String toString() {
    return "message [error=" + error + ", message=" + message + "]";
  }

}
