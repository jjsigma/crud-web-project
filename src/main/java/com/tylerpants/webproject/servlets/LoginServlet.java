package com.tylerpants.webproject.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("username", req.getParameter("username"));
        session.setAttribute("password", req.getParameter("password"));
        session.setMaxInactiveInterval(24*60*60);

        Cookie usernameCookie = new Cookie("username", req.getParameter("username"));
        usernameCookie.setMaxAge(24*60*60);
        resp.addCookie(usernameCookie);
        Cookie passwordCookie = new Cookie("password", req.getParameter("password"));
        passwordCookie.setMaxAge(24*60*60);
        resp.addCookie(passwordCookie);

        req.setAttribute("logged", true);
        System.out.println("Login servlet");
        resp.sendRedirect("/");
    }
}
