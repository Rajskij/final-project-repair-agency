package com.repair.agency.model.dao;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import com.repair.agency.model.service.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final Logger logger = LogManager.getLogger(UserDao.class.getName());
    private static final String SELECT_INVOICES_BY_EMAIL = "SELECT * FROM invoice WHERE user_id IN (SELECT id FROM user WHERE email=?)";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";
    private static final String GET_ENGINEER_BY_ID = "SELECT login FROM user WHERE id=?";
    private static final String CREATE_NEW_INVOICE = "INSERT INTO invoice (brand, model, description, user_id) VALUES (?, ?, ?, (SELECT id FROM user WHERE email=?))";
    private static final String INSERT_FEEDBACK = "UPDATE invoice SET feedback=? WHERE invoice_id=?";
    private static final String FINED_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String UPDATE_WALLET_BY_LOGIN = "UPDATE user SET wallet=? WHERE login=?";
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        }
        return user;
    }

    public User findUserByLogin(String login) {
        User user = new User();
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(FINED_USER_BY_LOGIN)) {
            prepStmt.setString(1, login);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                user = util.getUser(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        }
        return result;
    }

    public List<Invoice> selectInvoicesByEmail(String email) {
        List<Invoice> userInvoicesList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(SELECT_INVOICES_BY_EMAIL)) {
            prepStmt.setString(1, email);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Invoice invoice = util.getInvoice(rs);
                invoice.setEngineer(getEngineerById(invoice.getEngineer_id()));
                userInvoicesList.add(invoice);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return userInvoicesList;
    }

    private String getEngineerById(int id) {
        String name = "";
        try (Connection con = getConnection();
            PreparedStatement prepStmt = con.prepareStatement(GET_ENGINEER_BY_ID)) {
            prepStmt.setInt(1, id);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                name = rs.getString("login");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return name;
    }

    public boolean insertInvoice(String brand, String model, String description, String email) {
        boolean result = false;
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(CREATE_NEW_INVOICE)) {
            prepStmt.setString(1, brand);
            prepStmt.setString(2, model);
            prepStmt.setString(3, description);
            prepStmt.setString(4, email);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println(userDao.insertFeedback("nice job", "5"));
//        userDao.insertInvoice("Nokia", "3310", "still working", "user2@gmail.com");
//        userDao.selectInvoicesByEmail("user2@gmail.com").forEach(System.out::println);
    }

    public boolean insertFeedback(String comment, String id) {
        boolean result = false;
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(INSERT_FEEDBACK)) {
            prepStmt.setString(1, comment);
            prepStmt.setInt(2, Integer.parseInt(id));
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean updateWalletByLogin(String invoiceUser, BigDecimal price) {
        boolean result = false;
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(UPDATE_WALLET_BY_LOGIN)) {
            prepStmt.setBigDecimal(1, price);
            prepStmt.setString(2, invoiceUser);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(GET_USER_BY_EMAIL)) {
            prepStmt.setString(1, email);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                user = util.getUser(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }
}
