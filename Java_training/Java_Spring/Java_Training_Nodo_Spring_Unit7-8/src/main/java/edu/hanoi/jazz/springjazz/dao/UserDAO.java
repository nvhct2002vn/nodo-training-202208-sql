package edu.hanoi.jazz.springjazz.dao;

import edu.hanoi.jazz.springjazz.model.User;

import java.util.List;

public interface UserDAO {

    public void insert(User user);

    public List<User> list();

    public void delete(String username);

    public User get(String username);

    public void update(User user);

    public List<User> listByUsername(String usnername);
}
