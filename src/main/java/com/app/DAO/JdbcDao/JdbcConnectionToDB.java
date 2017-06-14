package com.app.DAO.JdbcDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by User on 04.06.2017.
 */
public class JdbcConnectionToDB {



//    private static final String url = "jdbc:postgresql://localhost:5432/jdbc_work";
//    private static final String username = "postgres";
//    private static final String password = "123581321lL";
//    private static final String driver = "org.postgresql.Driver";


    private static final String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "123581321lL";
    private static final String driver = "com.mysql.jdbc.Driver";

    protected static Connection getConnection() throws SQLException {
//        return null;
        return DriverManager.getConnection(url, username, password);
    }

}
