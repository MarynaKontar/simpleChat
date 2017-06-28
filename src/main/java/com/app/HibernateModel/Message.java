package com.app.HibernateModel;

import javax.persistence.*;
import java.util.Date;

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

@ManyToOne
//@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "user_login", referencedColumnName = "login")
   private User user;

@ManyToOne
//@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "chat_name", referencedColumnName = "name")
   private Chat chat;
@Column(name = "text")
   private String text;
@Column(name = "date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
@Temporal(TemporalType.TIMESTAMP)
   private Date messageDate;

   public Message() {
   }

   public Message(User user, Chat chat, String text) {
      this.user = user;
      this.chat = chat;
      this.text = text;
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
              ", user=" + user +
              ", chat=" + chat +
              ", text='" + text + '\'' +
              ", messageDate=" + messageDate +
              '}';
   }

}
