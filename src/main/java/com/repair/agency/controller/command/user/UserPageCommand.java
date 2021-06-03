package com.repair.agency.controller.command.user;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.UserDao;
import com.repair.agency.model.entity.Invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = new UserDao();
        String email = request.getParameter("email");
        List<Invoice> invoiceList = userDao.selectInvoicesByEmail(email);
        request.setAttribute("invoiceList", invoiceList);
        return Path.USER_PAGE;
    }
}
