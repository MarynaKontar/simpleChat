package com.app;

import com.app.DAO.HibernateDao.*;
import com.app.HibernateModel.Chat;
import com.app.HibernateModel.Group;
import com.app.HibernateModel.Message;
import com.app.HibernateModel.User;


import static com.app.Util.HibernateEntityInstance.*;

/**
 * Created by User on 26.06.2017.
 */
public class HibernateApplication {
    static int count = 1;

    public static void main(String[] args) {


        HibernateDAO<String, User> daoUser = new HibernateUserDao();
        HibernateDAO<String, Chat> daoChat = new HibernateChatDao();
        HibernateDAO<Long, Group> daoGroup = new HibernateGroupDao();
        HibernateDAO<Long, Message> daoMessage = new HibernateMessageDao();


//        Chat chat1 = getChatInstance(count);
//        daoChat.create(chat1);
//        Chat chat2 = getChatInstance(count + 1);
//        daoChat.create(chat2);
//
//        Group group1 = getGroupInstance(count);
//        daoGroup.create(group1);
//        Group group2 = getGroupInstance(count + 1 );
//        daoGroup.create(group2);
//
//        User user1 = getUserInstance(count);
//        daoUser.create(user1);
//        User user2= getUserInstance(count + 1);
//        daoUser.create(user2);

        Message message1 = getMessageInstance(count);
        daoMessage.create(message1);
        Message message2= getMessageInstance(count + 1);
        daoMessage.create(message2);

        
    }
}
