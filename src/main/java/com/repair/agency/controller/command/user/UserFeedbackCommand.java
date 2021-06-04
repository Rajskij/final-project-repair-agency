package com.repair.agency.controller.command.user;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFeedbackCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String brand = request.getParameter("invoiceBrand");
        String model = request.getParameter("invoiceModel");
        String id = request.getParameter("invoiceId");
        request.setAttribute("brand", brand);
        request.setAttribute("model", model);
        request.setAttribute("id", id);
        return Path.FEEDBACK_PAGE;
    }
}
