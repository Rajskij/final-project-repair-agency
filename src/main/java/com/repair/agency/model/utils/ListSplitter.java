package com.repair.agency.model.utils;

import com.repair.agency.model.dao.jdbc.JdbcAdminDao;
import com.repair.agency.model.entity.Invoice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ListSplitter {
    private static final Logger logger = LogManager.getLogger(JdbcAdminDao.class.getName());

    public List<Invoice> getListByPage(String pageNumber, int rowsOnPage, List<Invoice> invoiceList) {
        List<Invoice> pageInvoiceList = new ArrayList<>();

        if (pageNumber == null) {
            pageNumber = "1";
        }
        int page = Integer.parseInt(pageNumber);
        int min = (page - 1) * rowsOnPage;
        int max = min + rowsOnPage;
        int counter = 0;
        for (Invoice i : invoiceList) {
            counter++;
            if (counter > max) {
                break;
            }
            if (counter > min) {
                pageInvoiceList.add(i);
            }
        }
        return pageInvoiceList;
    }
}
