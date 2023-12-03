package com.example.jdbctest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdbctest.exceptions.DeleteException;
import com.example.jdbctest.exceptions.NotFoundUser;
import com.example.jdbctest.exceptions.UpsertException;
import com.example.jdbctest.models.users.User;
import com.example.jdbctest.models.users.UserDAOImpl;

@RestController("/api/users")
public class UsersControllers {
  @Autowired
  private final UserDAOImpl userDAO = new UserDAOImpl();

  @GetMapping("/hello")
  public String Hello() {
    return "Hello";
  }

  @GetMapping()
  public List<User> getAllUsers() {
    return userDAO.getAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable int id) throws NotFoundUser {
    try {
      return ResponseEntity.ok(userDAO.getUserById(id));
    } catch (Exception e) {
      throw new NotFoundUser(String.valueOf(id));
    }
  }

  @GetMapping("/email/{email}")
  public User getUserByEmail(@PathVariable String email) throws NotFoundUser {
    try {
      return userDAO.getUserByEmail(email);
    } catch (Exception e) {
      throw new NotFoundUser(email);
    }
  }

  @PostMapping("/create")
  public String createUser(User user) throws UpsertException {
    try {
      userDAO.createUser(user);
      return "User created successfully";
    } catch (Exception e) {
      throw new UpsertException();
    }
  }

  @PostMapping("/update")
  public String updateUser(User user) throws UpsertException {
    try {
      userDAO.updateUser(user);
      return "User updated successfully";
    } catch (Exception e) {
      throw new UpsertException();
    }
  }

  @DeleteMapping("/delete/{id}")
  public String deleteUser(@PathVariable int id) throws DeleteException {
    try {
      userDAO.getUserById(id);
      return userDAO.deleteUser(id) ? "User deleted" : "User not deleted";
    } catch (Exception e) {
      throw new DeleteException(id);
    }
  }
}
