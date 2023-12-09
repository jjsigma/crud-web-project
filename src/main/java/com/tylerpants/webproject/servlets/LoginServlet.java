package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.User;
import com.tylerpants.webproject.sql.UserSQLConnectorOld;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSQLConnectorOld connector = new UserSQLConnectorOld();
        try {
            if (!connector.checkIfUsernameExists(req.getParameter("username"))) {
                User user = new User(req.getParameter("username"), req.getParameter("password"));
                connector.createUser(user);
                Cookie userIdCookie = new Cookie("userId", user.getId() + "");
                userIdCookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(userIdCookie);
            } else {
                if (!connector.checkIfValid(req.getParameter("username"), req.getParameter("password"))) {
                    req.setAttribute("dataInvalid", true);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    int id = connector.getUserId(req.getParameter("username"));
                    Cookie userIdCookie = new Cookie("userId", id + "");
                    userIdCookie.setMaxAge(24 * 60 * 60);
                    resp.addCookie(userIdCookie);
                }
            }
            Cookie usernameCookie = new Cookie("username", req.getParameter("username"));
            usernameCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(usernameCookie);

            Cookie loggedCookie = new Cookie("logged", "true");
            loggedCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(loggedCookie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/");
    }
}
