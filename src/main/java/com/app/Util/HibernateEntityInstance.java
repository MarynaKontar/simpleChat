package com.app.Util;

import com.app.DAO.HibernateDao.HibernateGroupDao;
import com.app.HibernateModel.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 26.06.2017.
 */
public class HibernateEntityInstance {

    public static User getUserInstance(int number) {
        User user = new User("Login" + number, "Pass"  +  number, "userName" + number);
        Set<Group> groups = new HashSet<>();
//        groups.add(new Group( "group2"));
//        groups.add(new Group(2, "group2"));
//        groups = new HibernateGroupDao().getAll();
        groups.add(new HibernateGroupDao().read(1L).get());
        groups.add(new HibernateGroupDao().read(2L).get());
        user.setGroups(groups);
        return user;
    }


    public static Chat getChatInstance(int number) {
        return new Chat("Chat" + number, "Description!!!!"  +  number);
    }

    public static Group getGroupInstance(int number) {
        return new Group("group" + number);
    }

    public static Message getMessageInstance(int number) {

        Message message = new Message();
        message.setUser(new User("Login"+ number,"Pass"+ number,"userName" + number));
        message.setChat(new Chat("Chat" + (2), "description" + number));
        message.setText("Hello!!! this is message  num " + number);
        return message;

    }

    public static Message getMessageInstanceWithID(int number, long id) {
        Message message =getMessageInstance(number);
        message.setId(id);
        return message;

    }
}
