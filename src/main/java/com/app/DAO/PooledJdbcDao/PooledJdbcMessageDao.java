package com.app.DAO.PooledJdbcDao;

import com.app.DAO.JdbcDao.JdbcMessageDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 15.06.2017.
 */
public class PooledJdbcMessageDao extends JdbcMessageDao {

    private DataSource dataSource;

    public PooledJdbcMessageDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
