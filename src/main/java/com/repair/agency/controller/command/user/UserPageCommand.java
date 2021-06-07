package com.repair.agency.controller.command.user;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.service.UserService;
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
        UserService userService = new UserService();
        User user = new User();
        String email = (String) request.getSession().getAttribute("email");
        List<Invoice> invoiceList = userService.selectInvoicesByEmail(email);
        user = userService.getUserByEmail(email);
        request.setAttribute("wallet", user.getWallet());
        request.setAttribute("invoiceList", invoiceList);
        return Path.USER_PAGE;
    }
}
