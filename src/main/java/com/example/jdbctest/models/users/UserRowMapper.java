package com.example.jdbctest.models.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int arg1) throws SQLException {
    return new User(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("email"));
  }

}
