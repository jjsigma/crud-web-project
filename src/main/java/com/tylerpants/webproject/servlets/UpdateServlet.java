package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.Contact;
import com.tylerpants.webproject.sql.ContactsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-contact")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        ContactsDao contactsDao = new ContactsDao();
        Contact contact = (Contact) req.getAttribute("contact");
        System.out.println(contact);
        contactsDao.updateContact(contact);
        resp.sendRedirect("/");
    }
}
