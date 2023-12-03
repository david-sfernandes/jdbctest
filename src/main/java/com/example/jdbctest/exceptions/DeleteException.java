package com.example.jdbctest.exceptions;

public class DeleteException extends Exception {
  public DeleteException(Integer id) {
    super(DeleteException.generateMessage(id));
  }

  private static String generateMessage(Integer id) {
    return "Error on delete user with id: " + id;
  }
}
