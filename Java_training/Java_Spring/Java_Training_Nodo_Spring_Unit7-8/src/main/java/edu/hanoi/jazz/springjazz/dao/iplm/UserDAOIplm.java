package edu.hanoi.jazz.springjazz.dao.iplm;

import edu.hanoi.jazz.springjazz.dao.UserDAO;
import edu.hanoi.jazz.springjazz.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class UserDAOIplm implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(GroupDAOIplm.class));

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void insert(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            session.save(user);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("save user " + user.getUsername() + " done!");
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> list() {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery("from User");
        try {
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String username) {
        Session session = sessionFactory.getObject().openSession();
        User user = session.get(User.class, username);
        try {
            session.getTransaction().begin();
            session.delete(user);
            session.flush();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public User get(String username) {
        Session session = sessionFactory.getObject().openSession();
        return (User) session.get(User.class, username);
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getObject().openSession();
        User user1 = (User) session.merge(user);
        try {
            session.getTransaction().begin();
            session.save(user1);
            session.flush();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> listByUsername(String usnername) {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery("from User where username like :usnername");
        query.setParameter("usnername", "%" + usnername + "%");
        return query.list();
    }


}
