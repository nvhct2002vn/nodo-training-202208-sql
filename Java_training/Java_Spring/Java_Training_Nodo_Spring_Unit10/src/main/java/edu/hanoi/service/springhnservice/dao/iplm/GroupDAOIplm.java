package edu.hanoi.service.springhnservice.dao.iplm;

import edu.hanoi.service.springhnservice.dao.GroupDAO;
import edu.hanoi.service.springhnservice.model.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupDAOIplm implements GroupDAO {

    public LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Group> listGroup() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from Group ");
            List<Group> groups = query.list();
            return groups;
        } finally {
            session.close();
        }
    }
}
