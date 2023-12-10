package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.sql.entities.Contact;
import com.tylerpants.webproject.sql.dao.ContactsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactsDao contactsDao = new ContactsDao();
        Contact contact = contactsDao.getContactById(Integer.parseInt(req.getParameter("contactId")));
        contactsDao.deleteContact(contact);
        resp.sendRedirect("/");
    }
}
