package com.repair.agency.model.dao;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import com.repair.agency.model.service.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EngineerDao {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    private final String GET_ENGINEERS = "SELECT * FROM user WHERE role = 'ENGINEER'";
    private final String GET_INVOICES_BY_IS = "SELECT * FROM invoice WHERE engineer_id = ?";
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
            logger.severe(e.getMessage());
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
            logger.severe(e.getMessage());
        }
        return engineers;
    }

    public List<Invoice> getInvoicesById(int engineerId) {
        List<Invoice> invoiceList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(GET_INVOICES_BY_IS)) {
            prepStmt.setInt(1, engineerId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Invoice invoice = util.getInvoice(rs);
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return invoiceList;
    }

    public static void main(String[] args) {
        EngineerDao engineerDao = new EngineerDao();
        engineerDao.getInvoicesById(25).forEach(System.out::println);
    }
}
