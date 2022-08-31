package edu.hanoi.jazz.springjazz.dao.iplm;

import edu.hanoi.jazz.springjazz.dao.GroupDAO;
import edu.hanoi.jazz.springjazz.model.Group;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component("groupDAO")
public class GroupDAOIplm implements GroupDAO {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(GroupDAOIplm.class));
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void insert(Group group) {
        System.out.println(sessionFactory + ": Insert group " + group);
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            session.save(group);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("save group " + group.getName() + " done!");
        } finally {
            session.close();
        }
    }

    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery("from Group ");
        try {
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        if (null == group) {
            return;
        }
        try {
            session.getTransaction().begin();
            session.delete(group);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("Delete group " + group.getName() + " successful!");
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Group group) {
        Session session = sessionFactory.getObject().openSession();
        Group group1 = (Group) session.merge(group);
        try {
            session.getTransaction().begin();
            session.save(group1);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("update group " + group.getName() + " successful!");
        } finally {
            session.close();
        }
    }

    @Override
    public Group findID(Integer id) {
        Session session = sessionFactory.getObject().openSession();
        return session.get(Group.class, id);
    }

    @Override
    public List<Group> listByName(String name) {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery("from Group where name like :name");
        query.setParameter("name", "%" + name + "%");
        return query.list();
    }
}
