package com.repair.agency.model.dao.jdbc;

import com.repair.agency.model.dao.constanse.SqlConst;
import com.repair.agency.model.dao.UserDao;
import com.repair.agency.model.dao.maper.InvoiceMapper;
import com.repair.agency.model.dao.maper.UserMapper;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(JdbcUserDao.class.getName());
    private final Connection connection;
    InvoiceMapper invoiceMapper = new InvoiceMapper();
    UserMapper userMapper = new UserMapper();

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findUser(String email, String password) {
        User user = new User();
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.FINED_USER)) {
            prepStmt.setString(1, email);
            prepStmt.setString(2, password);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                user = userMapper.mapFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = new User();
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.FINED_USER_BY_LOGIN)) {
            prepStmt.setString(1, login);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                user = userMapper.mapFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean insertUser(String email, String login, String password) {
        if (email == null || login == null || password == null) {
            return false;
        }
        boolean result = false;
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.INSERT_USER)) {
            prepStmt.setString(1, email);
            prepStmt.setString(2, login);
            prepStmt.setString(3, password);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Invoice> selectInvoicesByEmail(String email) {
        Connection con = connection;
        List<Invoice> userInvoicesList = new ArrayList<>();
        try (PreparedStatement prepStmt = con.prepareStatement(SqlConst.SELECT_INVOICES_BY_EMAIL)) {
            con.setAutoCommit(false);
            prepStmt.setString(1, email);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Invoice invoice = invoiceMapper.mapFromResultSet(rs);
                invoice.setEngineer(getEngineerById(invoice.getEngineer_id()));
                userInvoicesList.add(invoice);
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException exception) {
                    logger.error(exception.getMessage());
                }
            }
        }
        return userInvoicesList;
    }

    private String getEngineerById(int id) throws SQLException {
        String name = "";
        PreparedStatement prepStmt = connection.prepareStatement(SqlConst.GET_ENGINEER_BY_ID);
        prepStmt.setInt(1, id);
        ResultSet rs = prepStmt.executeQuery();
        while (rs.next()) {
            name = rs.getString("login");
        }
        return name;
    }

    @Override
    public boolean insertInvoice(String brand, String model, String description, String email) {
        boolean result = false;
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.CREATE_NEW_INVOICE)) {
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

    @Override
    public boolean insertFeedback(String comment, String id) {
        boolean result = false;
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.INSERT_FEEDBACK)) {
            prepStmt.setString(1, comment);
            prepStmt.setInt(2, Integer.parseInt(id));
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateWalletByLogin(String invoiceUser, BigDecimal price) {
        boolean result = false;
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.UPDATE_WALLET_BY_LOGIN)) {
            prepStmt.setBigDecimal(1, price);
            prepStmt.setString(2, invoiceUser);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.GET_USER_BY_EMAIL)) {
            prepStmt.setString(1, email);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                user = userMapper.mapFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
