package edu.hanoi.jazz.springjazz.dao;

import edu.hanoi.jazz.springjazz.model.Group;

import java.util.List;

public interface GroupDAO {

    public void insert(Group group);

    public List<Group> list();

    public void delete(Integer id);

    public void update(Group group);

    public Group findID(Integer id);
}
