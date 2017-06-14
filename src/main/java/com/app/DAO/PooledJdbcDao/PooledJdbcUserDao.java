package com.app.DAO.PooledJdbcDao;


import com.app.DAO.JdbcDao.JdbcUserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 12.06.2017.
 */
public class PooledJdbcUserDao extends JdbcUserDao {

    private DataSource dataSource;

    public PooledJdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
