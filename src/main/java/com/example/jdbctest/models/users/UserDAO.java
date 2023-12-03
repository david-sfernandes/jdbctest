package com.example.jdbctest.models.users;

import java.util.List;

import com.example.jdbctest.exceptions.NotFoundUser;

sealed interface UserDAO permits UserDAOImpl {
  public User getUserById(int id) throws NotFoundUser;

  public User getUserByEmail(String email);

  public int createUser(User user);

  public int updateUser(User user);

  public boolean deleteUser(int id);

  public List<User> getAllUsers();
}
