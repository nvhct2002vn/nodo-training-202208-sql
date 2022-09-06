package edu.hanoi.service.springhnservice.dao.iplm;

import edu.hanoi.service.springhnservice.dao.UserDAO;
import edu.hanoi.service.springhnservice.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("userDAO")
public class UserDAOIplm implements UserDAO {

    private final static Logger LOGGER = Logger.getLogger(UserDAOIplm.class);

    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> listUser() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from User order by age desc ");
            return (List<User>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public String insert(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            Serializable value = session.save(user);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("Save user " + user.getUsername() + " done !" + value);
            return value.toString();
        } finally {
            session.close();
        }
    }

    @Override
    public User getUser(String username) {
        Session session = sessionFactory.getObject().openSession();
        try {
            return session.get(User.class, username);
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String username) {
        Session session = sessionFactory.getObject().openSession();
        User user = session.get(User.class, username);
        if (null == user) {
            return;
        }
        try {
            session.getTransaction().begin();
            session.delete(user);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            session.update(user);
            session.flush();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}

