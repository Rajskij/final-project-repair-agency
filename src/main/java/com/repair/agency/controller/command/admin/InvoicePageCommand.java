package com.repair.agency.controller.command.admin;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.jdbc.AdminDao;
import com.repair.agency.model.dao.jdbc.EngineerDao;
import com.repair.agency.model.dao.jdbc.UserDao;
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
        AdminDao adminDao = new AdminDao();
        UserDao userDao = new UserDao();
        EngineerDao engineerDao = new EngineerDao();
        String engineerId = request.getParameter("engineerId");
        String price = request.getParameter("price");
        String status = request.getParameter("status");
        String newUserWallet = request.getParameter("newWallet");
        String userLogin = request.getParameter("userLogin");
        int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));

        if (newUserWallet != null) {
            try {
                BigDecimal newWallet = new BigDecimal(newUserWallet);
                if (userDao.updateWalletByLogin(userLogin, newWallet)) {
                    status = "Paid";
                }
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
            }
        }
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
        BigDecimal userWallet = userDao.findUserByLogin(invoice.getUser()).getWallet();
        request.setAttribute("invoice", invoice);
        request.setAttribute("userWallet", userWallet);

        List<User> engineerList = engineerDao.getAllEngineers();
        request.setAttribute("engineerList", engineerList);
        return Path.INVOICE_PAGE;
    }

}

