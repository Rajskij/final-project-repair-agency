package com.repair.agency.model.dao.jdbc;

import com.repair.agency.model.dao.AdminDao;
import com.repair.agency.model.dao.DaoFactory;
import com.repair.agency.model.dao.EngineerDao;
import com.repair.agency.model.dao.UserDao;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AdminDao createAdminDao() {
        return new JdbcAdminDao(getConnection());
    }

    @Override
    public EngineerDao createEngineerDao() {
        return new JdbcEngineerDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(getConnection());
    }


}
