package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.sql.entities.Contact;
import com.tylerpants.webproject.sql.dao.ContactsDao;

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
        System.out.println(req.getParameter("name"));
        System.out.println(req.getParameter("surname"));
        System.out.println(req.getParameter("phone_number"));
        System.out.println(req.getParameter("contactId"));
        ContactsDao contactsDao = new ContactsDao();
        Contact contact = new Contact(Integer.parseInt(req.getParameter("contactId")), req.getParameter("name"), req.getParameter("surname"), req.getParameter("phone_number"));
        contactsDao.updateContact(contact);

        resp.sendRedirect("/");
    }
}
