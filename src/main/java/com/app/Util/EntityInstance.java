package com.app.Util;

import com.app.model.Chat;
import com.app.model.Group;
import com.app.model.Message;
import com.app.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 26.06.2017.
 */
public class EntityInstance {

    public static User getUserInstance(int number) {
        User user = new User("Login" + number, "Pass"  +  number, "userName" + number);
        Set<Group> groups = new HashSet<>();
        groups.add(new Group(2, "group2"));
        groups.add(new Group(2, "group2"));
        user.setGroups(groups);
        return user;
    }


    public static Chat getChatInstance(int number) {
        return new Chat("Chat" + number, "Description!!!!"  +  number);
    }

    public static Group getGroupInstance(int number) {
        return new Group(number,"group" + number);
    }

    public static Message getMessageInstance(int number) {

        Message message = new Message();
//            message.setId(number);
        message.setUserLogin("Login3");
        message.setChatName("Chat" + number);
        message.setText("Hello!!! this is message  num " + number);
        return message;

    }
}
