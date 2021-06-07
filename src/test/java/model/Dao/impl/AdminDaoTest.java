package model.Dao.impl;

import com.repair.agency.model.dao.jdbc.JdbcAdminDao;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class AdminDaoTest {
    Connection con;
    JdbcAdminDao adminDao;

    @BeforeClass
    public static void dbCreate() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost:3306?serverTimezone=EET";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/main/resources/db-test.sql"));
        sr.runScript(reader);
    }

    @Before
    public void jdbcCreate() throws SQLException {
        con = DBConnector.getDataSource().getConnection();
        adminDao = new JdbcAdminDao(con);
    }

    @Test
    public void testGetInvoiceById() {
        Invoice invoice = adminDao.getInvoiceById(2);
        assertNotNull(invoice);
    }

    @Test
    public void updateInvoiceEngineer() {
        Invoice invoice = adminDao.getInvoiceById(2);
        int currentEngineerId = invoice.getEngineer_id();
        adminDao.updateInvoiceEngineer(27,2);
        assertNotEquals(currentEngineerId, adminDao.getInvoiceById(2).getEngineer_id());
    }

    @Test
    public void updateInvoicePrice() {

    }

    @Test
    public void updateInvoiceStatus() {

    }

    @Test
    public void selectAllInvoices() {

    }

    @Test
    public void getAllUsers() {

    }

    @Test
    public void setUsersWallet() {

    }

    @Test
    public void getInvoiceByStatus() {

    }
}
