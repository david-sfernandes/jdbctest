package com.example.jdbctest.models.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.jdbctest.exceptions.NotFoundUser;

@Repository
public class UserRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
  private final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
  private final String CREATE_USER = "INSERT INTO users (name, email) VALUES (?, ?)";
  private final String UPDATE_USER = "UPDATE users SET name = ?, email = ? WHERE id = ?";
  private final String DELETE_USER = "DELETE FROM users WHERE id = ?";

  public User getUserById(int id) throws NotFoundUser {
    try {
      return jdbcTemplate.query(GET_USER_BY_ID, new UserRowMapper(), id).get(0);
    } catch (Exception e) {
      throw new NotFoundUser(String.valueOf(id));
    }
  }

  public User getUserByEmail(String email) {
    return jdbcTemplate.query(GET_USER_BY_EMAIL, new UserRowMapper(), email).get(0);
  }

  public int createUser(User user) {
    return jdbcTemplate.update(CREATE_USER, user.getName(), user.getEmail());
  }

  public int updateUser(User user) {
    return jdbcTemplate.update(UPDATE_USER, user.getName(), user.getEmail(), user.getId());
  }

  public boolean deleteUser(int id) {
    return jdbcTemplate.update(DELETE_USER, id) > 0;
  }

  public List<User> getAllUsers() {
    return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
  }
}
