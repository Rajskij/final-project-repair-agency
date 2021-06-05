package com.repair.agency.model.utils;

import com.repair.agency.model.dao.AdminDao;
import com.repair.agency.model.dao.EngineerDao;
import com.repair.agency.model.entity.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceFiler {
    public List<Invoice> filter(String status, String engineerEmail) {
        AdminDao adminDao = new AdminDao();
        EngineerDao engineerDao = new EngineerDao();
        List<Invoice> invoiceList = new ArrayList<>();
        if (status != null && !status.equals("All status")) {
            invoiceList = adminDao.getInvoiceByStatus(status);
        } else if (engineerEmail != null && !engineerEmail.equals("All")) {
            invoiceList = engineerDao.getInvoicesByEmail(engineerEmail);
        } else {
            invoiceList = adminDao.selectAllInvoices();
        }
        return invoiceList;
    }
}
