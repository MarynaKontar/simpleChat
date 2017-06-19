package com.app.Model;

import java.util.Date;

/**
 * Created by User on 09.06.2017.
 */
public class Message {

   private long id;
   private String userLogin;
   private String chatName;
   private String text;
   private Date messageDate;

   public Message() {
   }

   public Message(String userName, String chatName, String text) {
      this.userLogin = userName;
      this.chatName = chatName;
      this.text = text;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getUserLogin() {
      return userLogin;
   }

   public void setUserLogin(String userLogin) {
      this.userLogin = userLogin;
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
              ", userLogin='" + userLogin + '\'' +
              ", chatName='" + chatName + '\'' +
              ", text='" + text + '\'' +
              ", messageDate=" + messageDate +
              '}';
   }
}
