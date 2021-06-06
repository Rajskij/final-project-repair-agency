package com.repair.agency.controller.command.user;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.jdbc.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ThankYouCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = new UserDao();
        String comment = request.getParameter("comment");
        String id = request.getParameter("id");
        if (!userDao.insertFeedback(comment, id)) {
            return Path.ERROR_PAGE;
        }
        return Path.THANK_YOU_PAGE;
    }
}
