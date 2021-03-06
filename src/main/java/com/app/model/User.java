package com.app.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 09.06.2017.
 */
public class User {

   private String login;
   private String password;
   private String userName;
   private Timestamp registrationDate;
   Set<Group> groups;

   public User() {

   }

   public User(String login, String password, String userName) {
      this.login = login;
      this.password = password;
      this.userName = userName;

   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public Timestamp getRegistrationDate() {
      return registrationDate;
   }

   public void setRegistrationDate(Timestamp registrationDate) {
      this.registrationDate = registrationDate;
   }

   public Set<Group> getGroups() {
      return groups;
   }

   public void setGroups(Set<Group> groups) {
      this.groups = groups;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      User user = (User) o;

      return login != null ? login.equals(user.login) : user.login == null;
   }

   @Override
   public int hashCode() {
      return login != null ? login.hashCode() : 0;
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("User{");
      sb.append("login='").append(login).append('\'');
      sb.append(", userName='").append(userName).append('\'');
      sb.append(", registrationDate=").append(registrationDate);
      sb.append('}');
      return sb.toString();
   }
}
