package com.repair.agency.model.dao;

import com.repair.agency.model.dao.jdbc.AdminDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Sql {
    Logger logger = LogManager.getLogger(AdminDao.class.getName());

    String SET_USERS_WALLET = "UPDATE user SET wallet=? WHERE id=?";
    String UPDATE_INVOICE_ENGINEER = "UPDATE invoice SET engineer_id=? WHERE invoice_id=?";
    String UPDATE_INVOICE_PRICE = "UPDATE invoice SET price=? WHERE invoice_id=?";
    String UPDATE_INVOICE_STATUS = "UPDATE invoice SET status=? WHERE invoice_id=?";
    String SELECT_INVOICES_BY_STATUS = "SELECT * FROM invoice WHERE status=?";
    String FINED_INVOICE_BY_ID = "SELECT * FROM user inner JOIN invoice " +
            "ON invoice.invoice_id = ? and user.id = invoice.user_id or invoice.invoice_id = ? and user.id = invoice.engineer_id;";
    String SELECT_INVOICES = "SELECT * FROM invoice";
    String SELECT_ALL_USERS = "SELECT * FROM user WHERE role='USER'";
    String JDBC_URL = "jdbc:mysql://localhost:3306/repair?useSSL=false";
    String JDBC_USERNAME = "root";
    String JDBC_PASSWORD = "root";
}
