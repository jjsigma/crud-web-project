package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.Contact;
import com.tylerpants.webproject.sql.ContactsDao;
import com.tylerpants.webproject.sql.ContactsSQLConnectorOld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int userId = 0;
//        for (Cookie c : req.getCookies()) {
//            if (c.getName().equals("userId")) userId = Integer.parseInt(c.getValue());
//        }
//        ContactsSQLConnectorOld connector = new ContactsSQLConnectorOld(userId);
//        try {
//
//            int contactId = connector.getContactId(req.getParameter("name"), req.getParameter("surname"), req.getParameter("phone_number"));
//            connector.deleteContact(contactId);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        ContactsDao contactsDao = new ContactsDao();
        Contact contact = contactsDao.getContactById(Integer.parseInt(req.getParameter("contactId")));
        contactsDao.deleteContact(contact);
        resp.sendRedirect("/");
    }
}
