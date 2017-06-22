package com.app;


import com.app.DAO.DAOUser;
import com.app.DAO.HibernateDao.HibernateUserDao;
import com.app.DAO.PooledJdbcDao.PooledJdbcUserDao;
import com.app.model.Group;
import com.app.model.User;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12.06.2017.
 */
public class Application {
    static int count = 12;

    public static void main(String[] args) {


//        String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//        String username = "root";
//        String password = "123581321lL";
//        String DBDriver = "com.mysql.jdbc.Driver";
////        String username = System.getProperty("username");
////        String password = System.getProperty("password");
//
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setUrl(url);
//        dataSource.setPassword(password);
//        dataSource.setUsername(username);
//        dataSource.setInitialSize(5);
//
//        PooledJdbcUserDao daoUser = new PooledJdbcUserDao(dataSource);
//        daoUser.getAll().forEach(System.out::println);
//        System.out.println("--------------------");
//        daoUser.createUserWithMessagesExample(getUserInstance());
//
//        daoUser.getAll().forEach(System.out::println);

//        DAOUser daoUser1 = new HibernateUserDao();
//        User user = getUserInstance();
//        daoUser1.create(user);
    }


    private static User getUserInstance() {
        User user = new User("Login" + count, "Pass" + count, "userName" + count);
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(1, "group1"));
        groups.add(new Group(3, "group3"));
        user.setGroups(groups);
        count++;
        return user;
    }

}
