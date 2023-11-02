package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.User;
import com.tylerpants.webproject.sql.UserSQLConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
        UserSQLConnector connector = new UserSQLConnector();

//        session.setAttribute("username", req.getParameter("username"));
//        session.setAttribute("password", req.getParameter("password"));
//        session.setMaxInactiveInterval(24*60*60);

        Cookie usernameCookie = new Cookie("username", req.getParameter("username"));
        usernameCookie.setMaxAge(24*60*60);
        resp.addCookie(usernameCookie);

        Cookie passwordCookie = new Cookie("password", req.getParameter("password"));
        passwordCookie.setMaxAge(24*60*60);
        resp.addCookie(passwordCookie);

        Cookie loggedCookie = new Cookie("logged", "true");
        loggedCookie.setMaxAge(24*60*60);
        resp.addCookie(loggedCookie);

        try {
            if(!connector.checkIfUsernameExists(req.getParameter("username"))) {
                User user = new User(req.getParameter("username"), req.getParameter("password"));
                connector.createUser(user);
//                session.setAttribute("userId", user.getId());
                Cookie userIdCookie = new Cookie("userId", user.getId()+"");
                userIdCookie.setMaxAge(24*60*60);
                resp.addCookie(userIdCookie);
            } else {
                int id = connector.getUserId(req.getParameter("username"));
//                session.setAttribute("userId", id);
                Cookie userIdCookie = new Cookie("userId", id+"");
                userIdCookie.setMaxAge(24*60*60);
                resp.addCookie(userIdCookie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/");
    }
}
