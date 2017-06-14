package com.app.Model;

/**
 * Created by User on 09.06.2017.
 */
public class Chat {

   private String name;
   private String description;

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
