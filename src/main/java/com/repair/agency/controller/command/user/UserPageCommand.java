package com.repair.agency.controller.command.user;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.jdbc.UserDao;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = new UserDao();
        User user = new User();
        String email = (String) request.getSession().getAttribute("email");
        List<Invoice> invoiceList = userDao.selectInvoicesByEmail(email);
        user = userDao.getUserByEmail(email);
        request.setAttribute("wallet", user.getWallet());
        request.setAttribute("invoiceList", invoiceList);
        return Path.USER_PAGE;
    }
}
