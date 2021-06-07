package com.repair.agency.controller.command.user;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.service.UserService;
import com.repair.agency.model.entity.Invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddInvoiceCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = new UserService();
        String email = (String) request.getSession().getAttribute("email");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String description = request.getParameter("description");

        if (!userService.insertInvoice(brand, model, description, email)) {
            return Path.ERROR_PAGE;
        }
        List<Invoice> invoiceList = userService.selectInvoicesByEmail(email);
        request.setAttribute("invoiceList", invoiceList);
        return Path.USER_PAGE;
    }
}
