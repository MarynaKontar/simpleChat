package com.app.Model;

import java.util.Date;

/**
 * Created by User on 09.06.2017.
 */
public class Message {

   private String id;
   private User user;
   private Chat chat;
   private String text;
   private Date messageDate;

   public Message() {
   }

   public Message(User user, Chat chat, String text, Date messageDate) {
      this.id = user.getLogin() + chat.getName();
      this.user = user;
      this.chat = chat;
      this.text = text;
      this.messageDate = messageDate;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Chat getChat() {
      return chat;
   }

   public void setChat(Chat chat) {
      this.chat = chat;
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
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Message message = (Message) o;

      return id != null ? id.equals(message.id) : message.id == null;
   }

   @Override
   public int hashCode() {
      return id != null ? id.hashCode() : 0;
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("Message{");
      sb.append("id='").append(id).append('\'');
      sb.append(", user=").append(user);
      sb.append(", chat=").append(chat);
      sb.append(", text='").append(text).append('\'');
      sb.append(", messageDate=").append(messageDate);
      sb.append('}');
      return sb.toString();
   }
}
