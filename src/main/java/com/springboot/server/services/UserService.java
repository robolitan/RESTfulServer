package com.springboot.server.services;

import com.springboot.server.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void addUser(User user);

    void editUser(User user);

    void deleteUser(int id);

    User getUser(int id);

    User getUserByLogin(String login);
}
