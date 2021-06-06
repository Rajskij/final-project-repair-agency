package com.repair.agency.controller.command.admin;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.jdbc.JdbcAdminDao;
import com.repair.agency.model.dao.jdbc.JdbcEngineerDao;
import com.repair.agency.model.dao.jdbc.JdbcUserDao;
import com.repair.agency.model.dao.service.AdminService;
import com.repair.agency.model.dao.service.EngineerService;
import com.repair.agency.model.dao.service.UserService;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class InvoicePageCommand extends Command {
    private static final Logger logger = LogManager.getLogger(InvoicePageCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminService adminService = new AdminService();
        //JdbcAdminDao adminDao = new JdbcAdminDao();
        UserService userService = new UserService();
        //JdbcUserDao userDao = new JdbcUserDao();
        EngineerService engineerService = new EngineerService();
        //JdbcEngineerDao engineerDao = new JdbcEngineerDao();
        String engineerId = request.getParameter("engineerId");
        String price = request.getParameter("price");
        String status = request.getParameter("status");
        String newUserWallet = request.getParameter("newWallet");
        String userLogin = request.getParameter("userLogin");
        int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));

        if (newUserWallet != null) {
            try {
                BigDecimal newWallet = new BigDecimal(newUserWallet);
                if (userService.updateWalletByLogin(userLogin, newWallet)) {
                    status = "Paid";
                }
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
            }
        }
        if (engineerId != null) {
            int engId = Integer.parseInt(engineerId);
            adminService.updateInvoiceEngineer(engId, invoiceId);
        }
        if (price != null) {
            adminService.updateInvoicePrice(price, invoiceId);
        }
        if (status != null) {
            adminService.updateInvoiceStatus(status, invoiceId);
        }
        Invoice invoice = adminService.getInvoiceById(invoiceId);
        BigDecimal userWallet = userService.findUserByLogin(invoice.getUser()).getWallet();
        request.setAttribute("invoice", invoice);
        request.setAttribute("userWallet", userWallet);

        List<User> engineerList = engineerService.getAllEngineers();
        request.setAttribute("engineerList", engineerList);
        return Path.INVOICE_PAGE;
    }

}

