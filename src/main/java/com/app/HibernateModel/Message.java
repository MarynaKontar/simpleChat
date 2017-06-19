package com.app.HibernateModel;

import java.util.Date;

/**
 * Created by User on 09.06.2017.
 */
public class Message {

   private String id;
   private String userName;
   private String chatName;
   private String text;
   private Date messageDate;

   public Message() {
   }

   public Message(String userName, String chatName, String text) {
      this.id = userName + chatName; //+ messageDate ???????
      this.userName = userName;
      this.chatName = chatName;
      this.text = text;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
//      this.id = userName + chatName + messageDate.toString();
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getChatName() {
      return chatName;
   }

   public void setChatName(String chatName) {
      this.chatName = chatName;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public Date getMessageDate() {
      return messageDate;
   }

   public void setMessageDate(Date messageDate) {
      this.messageDate = messageDate;
   }

   @Override
   public String toString() {
      return "Message{" +
              "id='" + id + '\'' +
              ", userName='" + userName + '\'' +
              ", chatName='" + chatName + '\'' +
              ", text='" + text + '\'' +
              ", messageDate=" + messageDate +
              '}';
   }
}
