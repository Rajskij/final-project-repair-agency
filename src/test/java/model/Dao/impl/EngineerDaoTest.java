package model.Dao.impl;

import com.repair.agency.model.dao.jdbc.JdbcEngineerDao;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Assert;
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

public class EngineerDaoTest {
    Connection con;
    JdbcEngineerDao engineerDao;

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
        engineerDao = new JdbcEngineerDao(con);
    }

    @Test
    public void getAllEngineers() {
        List<User> actualEngineers = engineerDao.getAllEngineers();
        Assert.assertEquals(3, actualEngineers.size());
    }

    @Test
    public void getInvoicesByEmail() {
        List<Invoice> actualInvoice = engineerDao.getInvoicesByEmail("master@gmail.com");
        Assert.assertEquals(11, actualInvoice.size());
    }

}
