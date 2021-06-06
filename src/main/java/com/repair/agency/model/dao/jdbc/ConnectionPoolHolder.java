package com.repair.agency.model.dao.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * sql-DB connection
 */
public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/repair?useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(JDBC_URL);
                    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    ds.setUsername(JDBC_USERNAME);
                    ds.setPassword(JDBC_PASSWORD);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
