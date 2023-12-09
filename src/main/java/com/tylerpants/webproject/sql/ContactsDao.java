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

    public ContactsDao() {
        this.configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Contact.class);
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }
    public List<Contact> getAllContacts(User user) {
        try(Session session = sessionFactory.openSession()) {
            String hql ="from Contact where user = :user";
            Query<Contact> query = session.createQuery(hql, Contact.class);
            query.setParameter("user", user);
            return query.list();
        }
    }
//    public int getContactId(Contact contact) {
//        int resultId = -1;
//        try(Session session = sessionFactory.openSession()) {
//            String hql = "select id from Contact where name = :name and surname = :surname and phoneNumber = :phone";
//            Query<Integer> query = session.createQuery(hql, int.class);
//            query.setParameter("name", contact.getName());
//            query.setParameter("surname", contact.getSurname());
//            query.setParameter("phone", contact.getPhoneNumber());
//            resultId = query.getSingleResult();
//        }
//        return resultId;
//    }
    public void addContact(Contact contact) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(contact);
            transaction.commit();
        }
    }
    public void updateContact(Contact contact) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Contact contact1 = session.get(Contact.class, contact.getId());
            contact1.setName(contact.getName());
            contact1.setPhoneNumber(contact.getPhoneNumber());
            contact1.setSurname(contact.getSurname());
            session.merge(contact1);
            transaction.commit();
        }
    }
    public void deleteContact(Contact contact) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(contact);
            transaction.commit();
            session.flush();
        }
    }
}
