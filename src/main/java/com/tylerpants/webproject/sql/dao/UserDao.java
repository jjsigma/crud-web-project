package com.tylerpants.webproject.sql.dao;

import com.tylerpants.webproject.sql.entities.Contact;
import com.tylerpants.webproject.sql.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
        User user;
        try(Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
        }
        return user;
    }
    public int getUserId(String username) {
        try(Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery("select id from User where username = :username", int.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }
    public void createUser(User user) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }
    public boolean isUsernameExists(String username) {
        try(Session session = sessionFactory.openSession()) {
            String hql = "from User where username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            return !query.list().isEmpty();
        }
    }
    public boolean isPasswordCorrect(String username, String password) {
        try(Session session = sessionFactory.openSession()) {
            Query<String> query = session.createQuery("select password from User where username = :username", String.class);
            query.setParameter("username", username);
            String temp = query.uniqueResult();
            return temp.equals(password);
        }
    }
}
