package com.tylerpants.webproject.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-contact")
public class AddContactServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Working with posts!");

        System.out.println(req.getParameter("name"));
        System.out.println(req.getParameter("surname"));
        System.out.println(req.getParameter("phone_number"));
        resp.sendRedirect("table.jsp");
    }
}
