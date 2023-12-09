package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.Contact;
import com.tylerpants.webproject.User;
import com.tylerpants.webproject.sql.ContactsDao;
import com.tylerpants.webproject.sql.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-contact")
public class AddContactServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int userId = 0;
        for (Cookie c : req.getCookies()) {
            if (c.getName().equals("userId")) userId = Integer.parseInt(c.getValue());
        }
        User user = new UserDao().getUserById(userId);
        ContactsDao contactsDao = new ContactsDao();
        Contact contact = new Contact(req.getParameter("name"), req.getParameter("surname"), req.getParameter("phone_number"), user);
        contactsDao.addContact(contact);

        resp.sendRedirect("/");
    }
}
