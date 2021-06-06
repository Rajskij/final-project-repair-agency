package com.repair.agency.controller.command.engineer;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.jdbc.JdbcEngineerDao;
import com.repair.agency.model.dao.service.EngineerService;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.utils.ListSplitter;
import com.repair.agency.model.utils.PagesCounter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EngineerPageCommand extends Command {
    public static final int ROWS_ON_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EngineerService engineerService = new EngineerService();
        //JdbcEngineerDao engineerDao = new JdbcEngineerDao();
        String email = (String) request.getSession().getAttribute("email");
        String page = request.getParameter("page");

        List<Invoice> invoiceList = engineerService.getInvoicesByEmail(email);
        int pages = new PagesCounter().count(invoiceList.size(), ROWS_ON_PAGE);
        List<Invoice> pageInvoiceList = new ListSplitter().getListByPage(page, ROWS_ON_PAGE, invoiceList);

        request.setAttribute("pages", pages);
        request.setAttribute("invoiceList", pageInvoiceList);
        return Path.ENGINEER_PAGE;
    }
}

