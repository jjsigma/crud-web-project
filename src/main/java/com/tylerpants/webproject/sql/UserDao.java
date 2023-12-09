package com.tylerpants.webproject.sql;

import com.tylerpants.webproject.Contact;
import com.tylerpants.webproject.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserDao {
    private Configuration configuration;
    private SessionFactory sessionFactory;

    public UserDao() {
        this.configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Contact.class);
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }
    public User getUserById(int id) {
        User user = null;
        try(Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
        }
        return user;
    }
}
