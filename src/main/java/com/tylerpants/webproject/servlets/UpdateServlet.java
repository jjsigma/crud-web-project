package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.sql.ContactsSQLConnectorOld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-contact")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        int userId = 0;
        for (Cookie c : req.getCookies()) {
            if (c.getName().equals("userId")) userId = Integer.parseInt(c.getValue());
        }
        ContactsSQLConnectorOld connector = new ContactsSQLConnectorOld(userId);
        int contactId = Integer.parseInt(req.getParameter("contactId"));
        try {
            connector.updateContact(contactId, req.getParameter("name"), req.getParameter("surname"), req.getParameter("phone_number"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/");
    }
}
