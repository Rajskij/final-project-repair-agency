package model.Dao.impl;

import com.repair.agency.model.dao.jdbc.JdbcAdminDao;

import java.sql.Connection;

public class AdminDaoTest {
    Connection con;
    JdbcAdminDao adminDao;
/*
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
        jdbcCarDao = new JDBCCarDao(con);
    }*/

}
