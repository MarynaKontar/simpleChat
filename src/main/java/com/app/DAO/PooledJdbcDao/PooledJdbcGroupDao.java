package com.app.DAO.PooledJdbcDao;

import com.app.DAO.JdbcDao.JdbcGroupsDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 15.06.2017.
 */
public class PooledJdbcGroupDao extends JdbcGroupsDao {

    private DataSource dataSource;

    public PooledJdbcGroupDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}