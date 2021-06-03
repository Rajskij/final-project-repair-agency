package com.repair.agency.model.dao;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import com.repair.agency.model.service.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    private static final String SELECT_INVOICES_BY_EMAIL = "SELECT * FROM invoice";
    private final String FINED_USER = "SELECT * FROM user WHERE email = ? and password = ?";
    private final String INSERT_USER = "INSERT INTO user (email, login, password) VALUES (?, ?, ?)";
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

    public User findUser(String email, String password) {
        User user = new User();
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(FINED_USER)) {
            prepStmt.setString(1, email);
            prepStmt.setString(2, password);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                user = util.getUser(rs);
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return user;
    }

    public boolean insertUser(String email, String login, String password) {
        if (email == null || login == null || password == null) {
            return false;
        }
        boolean result = false;
        try (Connection con = getConnection();
            PreparedStatement prepStmt = con.prepareStatement(INSERT_USER)) {
            prepStmt.setString(1, email);
            prepStmt.setString(2, login);
            prepStmt.setString(3, password);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        User user = new UserDao().findUser("user@gmail.com", "1");
        System.out.println(user.toString());
//        System.out.println(new UserDao().insertUser("misha@gmail.com", "Misha", "mishacool"));


    }

    public List<Invoice> selectInvoicesByEmail(String email) {
        List<Invoice> invoicesList = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_INVOICES_BY_EMAIL);
            while (rs.next()) {
                Invoice invoice = util.getInvoice(rs);
                invoicesList.add(invoice);
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return invoicesList;
    }


}
