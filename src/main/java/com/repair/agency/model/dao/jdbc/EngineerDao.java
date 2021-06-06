package com.repair.agency.model.dao.jdbc;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import com.repair.agency.model.dao.maper.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngineerDao {
    private static final Logger logger = LogManager.getLogger(AdminDao.class.getName());
    private final String GET_ENGINEERS = "SELECT * FROM user WHERE role = 'ENGINEER'";
    private final String GET_INVOICES_BY_EMAIL = "SELECT * FROM invoice WHERE engineer_id IN(SELECT id FROM user WHERE email=?)";
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/repair?useSSL=false";
    private final String JDBC_USERNAME = "root";
    private final String JDBC_PASSWORD = "root";
    private final Util util = new Util();

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }

    public List<User> getAllEngineers() {
        List<User> engineers = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(GET_ENGINEERS);
            while (rs.next()) {
                User user = util.getUser(rs);
                engineers.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return engineers;
    }

    public List<Invoice> getInvoicesByEmail(String engineerEmail) {
        List<Invoice> invoiceList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(GET_INVOICES_BY_EMAIL)) {
            prepStmt.setString(1, engineerEmail);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Invoice invoice = util.getInvoice(rs);
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return invoiceList;
    }

    public static void main(String[] args) {
        EngineerDao engineerDao = new EngineerDao();
        engineerDao.getInvoicesByEmail("master@gmail.com").forEach(System.out::println);
    }
}
