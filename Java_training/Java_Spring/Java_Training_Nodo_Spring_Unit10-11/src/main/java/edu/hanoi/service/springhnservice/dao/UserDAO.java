package edu.hanoi.service.springhnservice.dao;

import edu.hanoi.service.springhnservice.model.User;

import java.util.List;

public interface UserDAO {
    List<User> listUser();

    String insert(User user);

    User getUser(String username);

    void delete(String username);

    void update(User user);
}
