package com.app.HibernateModel;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by User on 09.06.2017.
 */
@Entity
@Table(name = "message")
public class Message {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
   private long id;
@Column(name = "user_login")
   private User user;
@Column(name = "chat_name")
   private Chat chat;
@Column(name = "text")
   private String text;
@Column(name = "date")
   private Timestamp messageDate;

   public Message() {
   }

   public Message(User user, Chat chat, String text, Timestamp messageDate) {
      this.user = user;
      this.chat = chat;
      this.text = text;
      this.messageDate = messageDate;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
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

   public Timestamp getMessageDate() {
      return messageDate;
   }

   public void setMessageDate(Timestamp messageDate) {
      this.messageDate = messageDate;
   }

   @Override
   public String toString() {
      return "Message{" +
              "id='" + id + '\'' +
              ", user=" + user +
              ", chat=" + chat +
              ", text='" + text + '\'' +
              ", messageDate=" + messageDate +
              '}';
   }

}
