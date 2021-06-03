package com.repair.agency.controller.command;

import com.repair.agency.Path;
import com.repair.agency.controller.command.admin.AdminPageCommand;
import com.repair.agency.controller.command.engineer.EngineerPageCommand;
import com.repair.agency.controller.command.user.UserPageCommand;
import com.repair.agency.model.dao.UserDao;
import com.repair.agency.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ValidationPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = new UserDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = userDao.findUser(email, password);

        if ("ADMIN".equals(user.getRole())) {
            session.setAttribute("role", user.getRole());
           return new AdminPageCommand().execute(request, response);
        }

        if ("ENGINEER".equals(user.getRole())) {
            session.setAttribute("role", user.getRole());
           // request.setAttribute("engineerId", user.getId());
            return new EngineerPageCommand().execute(request, response);
        }

        if ("USER".equals(user.getRole())) {
            session.setAttribute("role", user.getRole());
            request.setAttribute("email",  email);
            request.setAttribute("password", password);
            request.setAttribute("userId", user.getId());
            return new UserPageCommand().execute(request, response);
        }
        return Path.ERROR_LOGIN;
    }
}
