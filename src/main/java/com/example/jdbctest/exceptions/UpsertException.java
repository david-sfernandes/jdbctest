package com.example.jdbctest.exceptions;

public class UpsertException extends Exception {
  public UpsertException() {
    super(UpsertException.generateMessage());
  }

  private static String generateMessage() {
    return "Error on create or update user! Check the data and try again.";
  }
}
