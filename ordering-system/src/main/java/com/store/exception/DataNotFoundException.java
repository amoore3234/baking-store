package com.store.exception;

public class DataNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DataNotFoundException(String message) {
    super(message);
  }

  public DataNotFoundException() {
    super("Entity not found");
  }

}
