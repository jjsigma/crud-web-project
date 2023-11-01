package com.tylerpants.webproject.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.regex.*;

@WebFilter("/login")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");

        Pattern usernamePattern = Pattern.compile("\\w{5,}");
        Matcher usernameMatcher = usernamePattern.matcher(username);
        if(!usernameMatcher.matches()) {
            System.out.println("Username is invaliD");
            servletRequest.setAttribute("usernameError", true);
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("login.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
        Pattern passwordPattern = Pattern.compile("\\w{8,}");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.matches()) {
            System.out.println("Password is invaliD");
            servletRequest.setAttribute("passwordError", true);
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("login.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
