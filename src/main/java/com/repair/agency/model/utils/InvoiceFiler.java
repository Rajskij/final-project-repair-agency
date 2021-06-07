package com.repair.agency.model.utils;

import com.repair.agency.model.dao.service.AdminService;
import com.repair.agency.model.dao.service.EngineerService;
import com.repair.agency.model.entity.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceFiler {
    public List<Invoice> filter(String status, String engineerEmail) {
        AdminService adminService = new AdminService();
        EngineerService engineerService = new EngineerService();
        List<Invoice> invoiceList = new ArrayList<>();
        if (status != null && !status.equals("All status")) {
            invoiceList = new AdminService().getInvoiceByStatus(status);
        } else if (engineerEmail != null && !engineerEmail.equals("All")) {
            invoiceList = engineerService.getInvoicesByEmail(engineerEmail);
        } else {
            invoiceList = adminService.selectAllInvoices();
        }
        return invoiceList;
    }
}
