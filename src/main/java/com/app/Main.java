package com.app;


import com.app.DAO.DAOUser;
import com.app.DAO.JdbcUserDao;
import com.app.Model.Group;
import com.app.Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.06.2017.
 */
public class Main {
    static int count = 1;

    public static void main(String[] args) throws SQLException {

        User user = getUserInstance();
        User user1 = getUserInstance();
        DAOUser daoUser = new JdbcUserDao();
//        daoUser.create(getUserInstance());
//        System.out.println(daoUser.read("Login1"));
//        System.out.println(daoUser.read("TEST"));
//        daoUser.delete(user);
//        daoUser.update(user1);
        daoUser.getAll().forEach(System.out::println);

//        daoUser.create(getUserInstance());
//        daoUser.create(getUserInstance());
//        daoUser.create(getUserInstance());


//        daoUser.deleteByKey("TEST");

//        daoUser.deleteByKey("Login4");
//        daoUser.deleteByKey("Login5");
//        daoUser.deleteByKey("Login6");
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


//     private boolean annagram(String str1, String str2){
//        if(str1.length()!=str2.length()) return false;
//char[] arr1 =str1.toCharArray();
//char[] arr2 =str2.toCharArray();
//         char[] arr3 = new char[str1.length() -1];
//         for (int i = 0; i < str1.length()-1 ; i++) {
//arr3[] =
//
//             Arrays.equals()
//
//
//         }
//     }


}
