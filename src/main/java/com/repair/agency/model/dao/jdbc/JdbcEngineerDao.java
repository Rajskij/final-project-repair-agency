package com.repair.agency.model.dao.jdbc;

import com.repair.agency.model.dao.EngineerDao;
import com.repair.agency.model.dao.constanse.SqlConst;
import com.repair.agency.model.dao.maper.InvoiceMapper;
import com.repair.agency.model.dao.maper.UserMapper;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import com.repair.agency.model.dao.maper.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEngineerDao implements EngineerDao {
    private static final Logger logger = LogManager.getLogger(JdbcAdminDao.class.getName());

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/repair?useSSL=false";
    private final String JDBC_USERNAME = "root";
    private final String JDBC_PASSWORD = "root";
    //private final Util util = new Util();
    UserMapper userMapper = new UserMapper();
    InvoiceMapper invoiceMapper = new InvoiceMapper();
    private Connection connection;


    public JdbcEngineerDao(Connection connection) {
        this.connection = connection;
    }

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

    @Override
    public List<User> getAllEngineers() {
        List<User> engineers = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SqlConst.GET_ENGINEERS);
            while (rs.next()) {
                User user = userMapper.mapFromResultSet(rs);
                engineers.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return engineers;
    }

    @Override
    public List<Invoice> getInvoicesByEmail(String engineerEmail) {
        List<Invoice> invoiceList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement prepStmt = con.prepareStatement(SqlConst.GET_INVOICES_BY_EMAIL)) {
            prepStmt.setString(1, engineerEmail);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Invoice invoice = invoiceMapper.mapFromResultSet(rs);
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return invoiceList;
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
