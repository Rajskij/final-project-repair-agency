package com.repair.agency.model.dao;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import com.repair.agency.model.service.Util;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminDao {
    private static final Logger logger = LogManager.getLogger(AdminDao.class.getName());
    private static final String SET_USERS_WALLET = "UPDATE user SET wallet=? WHERE id=?";
    private static final String UPDATE_INVOICE_ENGINEER = "UPDATE invoice SET engineer_id=? WHERE invoice_id=?";
    private static final String UPDATE_INVOICE_PRICE = "UPDATE invoice SET price=? WHERE invoice_id=?";
    private static final String UPDATE_INVOICE_STATUS = "UPDATE invoice SET status=? WHERE invoice_id=?";
    private final String FINED_INVOICE_BY_ID = "SELECT * FROM user inner JOIN invoice " +
            "ON invoice.invoice_id = ? and user.id = invoice.user_id or invoice.invoice_id = ? and user.id = invoice.engineer_id;";
    private final String UPDATE_INVOICE = "UPDATE invoice set price=?, status=?, engineer_id=? where invoice_id=?";
    private final String FINED_ENGINEER_ID_BY_NAME = "SELECT id FROM user WHERE login=?";
    private final String SELECT_INVOICES = "SELECT * FROM invoice";
    private final String SELECT_ALL_USERS = "SELECT * FROM user WHERE role='USER'";
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

    public boolean updateInvoiceEngineer(int engineerId, int invoiceId) {
        boolean result = false;
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(UPDATE_INVOICE_ENGINEER)) {
            prepStmt.setInt(1, engineerId);
            prepStmt.setInt(2, invoiceId);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean updateInvoicePrice(String price, int invoiceId) {
        boolean result = false;
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(UPDATE_INVOICE_PRICE)) {
            prepStmt.setBigDecimal(1, new BigDecimal(price));
            prepStmt.setInt(2, invoiceId);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean updateInvoiceStatus(String status, int invoiceId) {
        boolean result = false;
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(UPDATE_INVOICE_STATUS)) {
            prepStmt.setString(1, status);
            prepStmt.setInt(2, invoiceId);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public List<Invoice> selectAllInvoices() {
        List<Invoice> invoicesList = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_INVOICES);
            while (rs.next()) {
                Invoice invoice = util.getInvoice(rs);
                invoicesList.add(invoice);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return invoicesList;
    }

    public Invoice getInvoiceById(int id) {
        Invoice invoice = new Invoice();
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(FINED_INVOICE_BY_ID)) {
            prepStmt.setInt(1, id);
            prepStmt.setInt(2, id);
            ResultSet rs = prepStmt.executeQuery();

            String engineer = null;
            String user = null;
            while (rs.next()) {
                if (rs.getString("role").equals("ENGINEER")) {
                    engineer = rs.getString("login");
                }
                if (rs.getString("role").equals("USER")) {
                    user = rs.getString("login");
                }
                invoice = util.getInvoice(rs);
            }
            invoice.setUser(user);
            invoice.setEngineer(engineer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return invoice;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection con = getConnection();
            Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS);
            while (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setId(rs.getInt("id"));
                user.setRole(rs.getString("role"));
                user.setWallet(rs.getBigDecimal("wallet"));
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return userList;
    }

    public boolean setUsersWallet(String userId, String walletValue) {
        boolean result = false;

        try (Connection con = getConnection();
            PreparedStatement prepStmt = con.prepareStatement(SET_USERS_WALLET)) {
            BigDecimal wallet = new BigDecimal(walletValue);
            int id = Integer.parseInt(userId);
            prepStmt.setBigDecimal(1, wallet);
            prepStmt.setInt(2, id);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            return false;
        }
        return result;
    }

    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
//        System.out.println(adminDao.getInvoiceById(2));
//        int id = adminDao.finedEngineerByName("Petrov");
//        System.out.println(id);
//        boolean result = adminDao.updateInvoiceValues("78", "waiting for payment", "Petrov", 2);
//        System.out.println(result);
    }
}
