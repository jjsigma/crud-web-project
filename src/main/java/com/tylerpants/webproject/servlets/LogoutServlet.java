package com.tylerpants.webproject.servlets;

import com.tylerpants.webproject.sql.UserSQLConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.eraseCookie(req, resp);
        this.invalidateSession(req, resp);
        Cookie loggedInCookie = new Cookie("logged", "false");
        loggedInCookie.setMaxAge(-1);
        resp.addCookie(loggedInCookie);

        resp.sendRedirect("/");
    }

    private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
    }

    private void invalidateSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
    }

}
