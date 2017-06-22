package com.app.DAO.PooledJdbcDao;

import com.app.DAO.JdbcDao.JdbcChatDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 14.06.2017.
 */
public class PooledJdbcChatDao extends JdbcChatDao {
    private DataSource dataSource;

    public PooledJdbcChatDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
