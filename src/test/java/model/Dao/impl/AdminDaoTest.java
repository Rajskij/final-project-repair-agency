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

import static org.junit.Assert.*;

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
    public void getInvoiceByStatusTest() {
        List<Invoice> invoiceList = adminDao.getInvoiceByStatus("Done");
        assertEquals(invoiceList.size(), 1);
    }

    @Test
    public void testGetInvoiceByIdTest() {
        Invoice invoice = adminDao.getInvoiceById(2);
        assertNotNull(invoice);
    }

    @Test
    public void updateInvoiceEngineerTest() {
        boolean result = adminDao.updateInvoiceEngineer(1,2);
        assertTrue(result);
    }

    @Test
    public void updateInvoicePriceTest() {
        boolean result = adminDao.updateInvoicePrice("777", 1);
        assertTrue(result);
    }

    @Test
    public void updateInvoiceStatusTest() {
        boolean result = adminDao.updateInvoiceStatus("DONE", 1);
        assertTrue(result);
    }

    @Test
    public void selectAllInvoicesTest() {
        List<Invoice> invoices = adminDao.selectAllInvoices();
        assertEquals(invoices.size(), 3);
    }

    @Test
    public void getAllUsersTest() {
        List<User> list = adminDao.getAllUsers();
        assertEquals(list.size(), 1);
    }

    @Test
    public void setUsersWalletTest() {
        boolean result = adminDao.setUsersWallet("3", "70.00");
        assertTrue(result);
    }
}
