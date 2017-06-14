package com.app;


import com.app.DAO.DAOUser;
import com.app.DAO.JdbcUserDao;
import com.app.Model.User;

import java.sql.SQLException;

/**
 * Created by User on 09.06.2017.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        //

//        User user = new User("Login1", "Pass1", "userName1");

        DAOUser daoUser = new JdbcUserDao();
//        daoUser.create(user);
        System.out.println(daoUser.read("Login1"));
        System.out.println(daoUser.read("TEST"));

//        Connection con = getConnection();
//        try(Statement statement = con.createStatement()){
//
//            try(ResultSet set = statement.executeQuery("SELECT LOGIN, PASS, USERNAME, REGISTRATION_DATE FROM users" )){
//
//                while (set.next()){
//                    String login = set.getString(1);
//                    String pass = set.getString(2);
//                    String user = set.getString(3);
//                    Timestamp regDate = set.getTimestamp(4);
//                    System.out.println(login + ";" + pass + ";" + user + ";" + regDate);
//                }
//            }
//
//        }



//        try(PreparedStatement statement = con.prepareStatement("SELECT LOGIN, PASS, USERNAME, REGISTRATION_DATE FROM users where USERNAME = ?")){
//
//            statement.setString(1, "TEST");
//            try(ResultSet set = statement.executeQuery()){
//
//                while (set.next()){
//                    String login = set.getString(1);
//                    String pass = set.getString(2);
//                    String user = set.getString(3);
//                    Timestamp regDate = set.getTimestamp(4);
//                    System.out.println(login + ";" + pass + ";" + user + ";" + regDate);
//                }
//            }
//
//        }
//
//
//        String sql="insert into users (LOGIN, PASS, USERNAME, REGISTRATION_DATE) values (?,?,?,?)";
//
//        try(PreparedStatement statement = con.prepareStatement(sql)){
//            statement.setString(1,"Vasya");
//            statement.setString(2,"111");
//            statement.setString(3,"vasya_username");
//            statement.setNull(4, Types.TIMESTAMP);
//           int r = statement.executeUpdate();
//            System.out.println(r);
//        }


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
