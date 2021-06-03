package com.repair.agency.controller.command;

import com.repair.agency.Path;
import com.repair.agency.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterSuccessPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = new UserDao();
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean result = userDao.insertUser(email, login, password);
       // request.setAttribute("login", login);
        return result ? new ValidationPageCommand().execute(request, response) : Path.ERROR_PAGE;
    }
}
