package com.repair.agency.controller.command;

import com.repair.agency.Path;
import com.repair.agency.controller.command.admin.AdminPageCommand;
import com.repair.agency.controller.command.engineer.EngineerPageCommand;
import com.repair.agency.controller.command.user.UserPageCommand;
import com.repair.agency.model.dao.jdbc.JdbcUserDao;
import com.repair.agency.model.dao.service.UserService;
import com.repair.agency.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ValidationPageCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ValidationPageCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = new UserService();
        //JdbcUserDao userDao = new JdbcUserDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = userService.findUser(email, password);

        if ("ADMIN".equals(user.getRole())) {
            session.setAttribute("role", user.getRole());
            logger.info("Admin is log in");
            return new AdminPageCommand().execute(request, response);
        }

        if ("ENGINEER".equals(user.getRole())) {
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", email);
            return new EngineerPageCommand().execute(request, response);
        }

        if ("USER".equals(user.getRole())) {
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", email);
            return new UserPageCommand().execute(request, response);
        }
        return Path.ERROR_LOGIN;
    }
}
