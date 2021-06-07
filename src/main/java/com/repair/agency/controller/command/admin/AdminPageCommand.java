package com.repair.agency.controller.command.admin;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.service.EngineerService;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import com.repair.agency.model.utils.InvoiceFiler;
import com.repair.agency.model.utils.ListSplitter;
import com.repair.agency.model.utils.PagesCounter;
import com.repair.agency.model.utils.TableSorter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdminPageCommand extends Command {

    public static final int ROWS_ON_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EngineerService engineerService = new EngineerService();
        String status = request.getParameter("status");
        String engineerEmail = request.getParameter("engineerEmail");
        String sortingType = request.getParameter("sortingType");
        String page = request.getParameter("page");
        HttpSession session = request.getSession();

        List<User> engineerList = engineerService.getAllEngineers();
        List<String> statusList = Arrays.asList("Payment expected", "Paid", "Canceled", "In work", "Done", "All status");
        List<String> sortByList = Arrays.asList("ByDate", "ByStatus", "ByPrice");
        List<Invoice> invoiceList = new InvoiceFiler().filter(status, engineerEmail);

        if (sortingType != null) {
            session.setAttribute("sort", sortingType);
        }

        new TableSorter().sort((String) session.getAttribute("sort"), invoiceList);
        int pages = new PagesCounter().count(invoiceList.size(), ROWS_ON_PAGE);
        List<Invoice> pageInvoiceList = new ListSplitter().getListByPage(page, ROWS_ON_PAGE, invoiceList);

        request.setAttribute("sortBy", sortByList);
        request.setAttribute("pages", pages);
        request.setAttribute("invoiceList", pageInvoiceList);
        request.setAttribute("statusList", statusList);
        request.setAttribute("engineerList", engineerList);
        return Path.ADMIN_PAGE;
    }
}
