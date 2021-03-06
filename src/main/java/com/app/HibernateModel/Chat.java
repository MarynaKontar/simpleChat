package com.app.HibernateModel;

import javax.persistence.*;

/**
 * Created by User on 09.06.2017.
 */
@Entity
//@Embeddable
@Table(name = "chat")
@NamedQueries({
        @NamedQuery(name = "Chat.findAll", query = "select c FROM com.app.HibernateModel.Chat c"),
        @NamedQuery(name = "Chat.countAll", query = "Select count(c) FROM Chat c")
})
public class Chat {
@Id
@Column(name = "name")
   private String name;
@Column(name = "description")
   private String description;

   public Chat() {
   }

   public Chat(String name, String description) {
      this.name = name;
      this.description = description;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Chat chat = (Chat) o;

      return name != null ? name.equals(chat.name) : chat.name == null;
   }

   @Override
   public int hashCode() {
      return name != null ? name.hashCode() : 0;
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("Chat{");
      sb.append("name='").append(name).append('\'');
      sb.append(", description='").append(description).append('\'');
      sb.append('}');
      return sb.toString();
   }
}
