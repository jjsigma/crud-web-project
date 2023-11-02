package com.tylerpants.webproject.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/add")
public class AddFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        int userId = -1;
        for(Cookie c : ((HttpServletRequest) servletRequest).getCookies()) {
            if(c.getName().equals("userId")) userId = Integer.parseInt(c.getValue());
        }
        if(userId == -1) {
            servletRequest.setAttribute("notLogged", "true");
            ((HttpServletResponse) servletResponse).sendRedirect("/");
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
