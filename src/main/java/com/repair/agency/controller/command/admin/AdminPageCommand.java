package com.repair.agency.controller.command.admin;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.AdminDao;
import com.repair.agency.model.entity.Invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminDao adminDao = new AdminDao();
        List<Invoice> invoiceList = adminDao.selectAllInvoices();
        request.setAttribute("invoiceList", invoiceList);
        return Path.ADMIN_PAGE;
    }
}
