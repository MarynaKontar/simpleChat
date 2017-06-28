package com.app;


import com.app.DAO.DAOUser;
import com.app.DAO.HibernateDao.HibernateUserDao;
import com.app.DAO.PooledJdbcDao.PooledJdbcChatDao;
import com.app.DAO.PooledJdbcDao.PooledJdbcGroupDao;
import com.app.DAO.PooledJdbcDao.PooledJdbcMessageDao;
import com.app.DAO.PooledJdbcDao.PooledJdbcUserDao;
import com.app.model.Chat;
import com.app.model.Group;
import com.app.model.Message;
import com.app.model.User;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.app.Util.EntityInstance.*;

/**
 * Created by User on 12.06.2017.
 */
public class Application {
    static int count = 4;

    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/chat?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123581321lL";
//        String DBDriver = "com.mysql.jdbc.Driver";
////        String username = System.getProperty("username");
////        String password = System.getProperty("password");
//
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        dataSource.setInitialSize(5);
//
        PooledJdbcUserDao daoUser = new PooledJdbcUserDao(dataSource);
        PooledJdbcChatDao daoChat = new PooledJdbcChatDao(dataSource);
        PooledJdbcGroupDao daoGroup = new PooledJdbcGroupDao(dataSource);
        PooledJdbcMessageDao daoMessage = new PooledJdbcMessageDao(dataSource);

//        ----------------------User----------------------------
        daoUser.getAll().forEach(System.out::println);
        System.out.println("--------------------");

        daoUser.createUserWithMessagesExample(getUserInstance(count));
        daoUser.update(getUserInstance(count));
        System.out.println(daoUser.read("Login4"));
        daoUser.getAll().forEach(System.out::println);


//        ----------------------Chat----------------------------
        daoChat.getAll().forEach(System.out::println);
        System.out.println("--------------------");
        daoChat.create(getChatInstance(4));
        daoChat.update(getChatInstance(0));
        daoChat.delete(getChatInstance(0));
        System.out.println(daoChat.read("Chat" + 4));
        daoChat.getAll().forEach(System.out::println);



//        ----------------------Group----------------------------
        daoGroup.getAll().forEach(System.out::println);
        System.out.println("--------------------");
        daoGroup.create(getGroupInstance(count));
        daoGroup.update(getGroupInstance(count));
        daoGroup.delete(getGroupInstance(count));
        System.out.println(daoGroup.read(4L));
        daoGroup.getAll().forEach(System.out::println);




//        ----------------------Message----------------------------
        daoMessage.getAll().forEach(System.out::println);
        System.out.println("--------------------");
        daoMessage.create(getMessageInstance(count));
        daoMessage.update(getMessageInstance(count));
        daoMessage.delete(getMessageInstance(count));
        System.out.println(daoMessage.read(4L));
        daoMessage.getAll().forEach(System.out::println);

    }
}
