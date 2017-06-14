package com.app;


import com.app.DAO.DAOUser;
import com.app.DAO.PooledJdbcUserDao;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * Created by User on 12.06.2017.
 */
public class Application {

    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123581321lL";
        String DBDriver = "com.mysql.jdbc.Driver";
//        String username = System.getProperty("username");
//        String password = System.getProperty("password");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(username);
//        dataSource.setInitialSize(5);

        DAOUser daoUser = new PooledJdbcUserDao(dataSource);
        daoUser.getAll().forEach(System.out::println);
    }
}
