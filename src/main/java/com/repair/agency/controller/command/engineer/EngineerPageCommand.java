package com.repair.agency.controller.command.engineer;

import com.mysql.cj.Session;
import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.EngineerDao;
import com.repair.agency.model.entity.Invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EngineerPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EngineerDao engineerDao = new EngineerDao();
        //String email = (String) request.getSession().getAttribute("email");
        String email = (String) request.getSession().getAttribute("email");
        List<Invoice> invoiceList = engineerDao.getInvoicesByEmail(email);
        request.setAttribute("invoiceList", invoiceList);
        return Path.ENGINEER_PAGE;
    }
}

