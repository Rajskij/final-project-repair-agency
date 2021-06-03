package com.repair.agency.controller.filter;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String lang = "en";

        // если в сесии такой атрибут есть то берем из сесии
        if (session.getAttribute("lang") != null) {
            lang = (String) session.getAttribute("lang");
        }
        // если в реквесте есть параметр то перезаписываем "ланг"
        if (request.getParameter("lang") != null) {
            lang = request.getParameter("lang");
        }
        // по итогу пишем значение переменной
        session.setAttribute("lang", lang);

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }

}
