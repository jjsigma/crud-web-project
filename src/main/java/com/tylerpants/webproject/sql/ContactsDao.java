package com.tylerpants.webproject.sql;

import com.tylerpants.webproject.Contact;
import com.tylerpants.webproject.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ContactsDao {
    private Configuration configuration;
    private SessionFactory sessionFactory;
    private int userId;

    public ContactsDao(int userId) {
        this.configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Contact.class);
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        this.userId = userId;
    }
    public List<Contact> getAllContacts() {
        try(Session session = sessionFactory.openSession()) {
            String hql ="from Contact where userId = :userId";
            Query<Contact> query = session.createQuery(hql, Contact.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }
    public void addContact(Contact contact) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(contact);
            transaction.commit();
        }
    }
    public void updateContact(Contact contact) {
        try(Session session = sessionFactory.openSession()) {

        }
    }
    public void deleteContact(Contact contact) {

    }
}
