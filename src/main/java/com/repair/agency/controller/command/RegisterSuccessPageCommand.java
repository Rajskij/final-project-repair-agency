package com.repair.agency.controller.command;

import com.repair.agency.Path;
import com.repair.agency.model.dao.jdbc.JdbcUserDao;
import com.repair.agency.model.dao.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterSuccessPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = new UserService();
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean result = userService.insertUser(email, login, password);
        return result ? new ValidationPageCommand().execute(request, response) : Path.ERROR_PAGE;
    }
}
