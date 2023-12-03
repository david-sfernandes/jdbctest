package com.example.jdbctest.exceptions;

public class NotFoundUser extends Exception{
  public NotFoundUser(String key) {
    super(NotFoundUser.generateMessage(key));
  }

  private static String generateMessage(String key) {
    return "User not found with key: " + key;
  }
}
