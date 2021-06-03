package com.repair.agency.controller.command.admin;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.AdminDao;
import com.repair.agency.model.dao.EngineerDao;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InvoicePageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminDao adminDao = new AdminDao();
        EngineerDao engineerDao = new EngineerDao();
        String engineerId = request.getParameter("engineerId");
        String price = request.getParameter("price");
        String status = request.getParameter("status");
        int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));

        if (engineerId != null) {
            int engId = Integer.parseInt(engineerId);
            adminDao.updateInvoiceEngineer(engId, invoiceId);
        }
        if (price != null) {
            adminDao.updateInvoicePrice(price, invoiceId);
        }
        if (status != null) {
            adminDao.updateInvoiceStatus(status, invoiceId);
        }
        Invoice invoice = adminDao.getInvoiceById(invoiceId);
        request.setAttribute("invoice", invoice);

        List<User> engineerList = engineerDao.getAllEngineers();
        request.setAttribute("engineerList", engineerList);
        return Path.INVOICE_PAGE;
    }

}

