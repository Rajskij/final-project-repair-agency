package com.repair.agency.model.dao.jdbc;

import com.repair.agency.model.dao.AdminDao;
import com.repair.agency.model.dao.constanse.SqlConst;
import com.repair.agency.model.dao.maper.InvoiceMapper;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcAdminDao implements AdminDao {
    private static final Logger logger = LogManager.getLogger(JdbcAdminDao.class.getName());
    private final Connection connection;
    InvoiceMapper invoiceMapper = new InvoiceMapper();

    public JdbcAdminDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean updateInvoiceEngineer(int engineerId, int invoiceId) {
        boolean result = false;
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.UPDATE_INVOICE_ENGINEER)) {
            prepStmt.setInt(1, engineerId);
            prepStmt.setInt(2, invoiceId);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateInvoicePrice(String price, int invoiceId) {
        boolean result = false;
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.UPDATE_INVOICE_PRICE)) {
            prepStmt.setBigDecimal(1, new BigDecimal(price));
            prepStmt.setInt(2, invoiceId);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateInvoiceStatus(String status, int invoiceId) {
        boolean result = false;
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.UPDATE_INVOICE_STATUS)) {
            prepStmt.setString(1, status);
            prepStmt.setInt(2, invoiceId);
            result = prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Invoice> selectAllInvoices() {
        List<Invoice> invoicesList = new ArrayList<>();
        try (Connection con = connection;
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SqlConst.SELECT_INVOICES);
            while (rs.next()) {
                Invoice invoice = invoiceMapper.mapFromResultSet(rs);
                invoicesList.add(invoice);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return invoicesList;
    }

    @Override
    public Invoice getInvoiceById(int id) {
        Invoice invoice = new Invoice();
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.FINED_INVOICE_BY_ID)) {
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
                invoice = invoiceMapper.mapFromResultSet(rs);
            }
            invoice.setUser(user);
            invoice.setEngineer(engineer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return invoice;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection con = connection;
            Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SqlConst.SELECT_ALL_USERS);
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

    @Override
    public boolean setUsersWallet(String userId, String walletValue) {
        boolean result = false;

        try (Connection con = connection;
            PreparedStatement prepStmt = con.prepareStatement(SqlConst.SET_USERS_WALLET)) {
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

    @Override
    public List<Invoice> getInvoiceByStatus(String status) {
        List<Invoice> invoicesList = new ArrayList<>();
        try (Connection con = connection;
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.SELECT_INVOICES_BY_STATUS)) {
            prepStmt.setString(1, status);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Invoice invoice = invoiceMapper.mapFromResultSet(rs);
                invoicesList.add(invoice);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return invoicesList;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
