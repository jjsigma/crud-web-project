package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.sql.entities.User;
import com.tylerpants.webproject.sql.dao.UserDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDao userDao = new UserDao();

        if(!userDao.isUsernameExists(username)) {
            User user = new User(username, password);
            userDao.createUser(user);
            Cookie userIdCookie = new Cookie("userId", user.getId() + "");
            userIdCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(userIdCookie);
        } else {
            if(!userDao.isPasswordCorrect(username, password)) {
                req.setAttribute("dataInvalid", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            } else {
                int id = userDao.getUserId(username);
                Cookie userIdCookie = new Cookie("userId", id + "");
                userIdCookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(userIdCookie);
            }
        }
        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(usernameCookie);

        Cookie loggedCookie = new Cookie("logged", "true");
        loggedCookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(loggedCookie);

        resp.sendRedirect("/");
    }
}
