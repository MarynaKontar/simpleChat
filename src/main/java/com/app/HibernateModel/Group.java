package com.app.HibernateModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 12.06.2017.
 */
@Entity
//@Embeddable
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    //в оракле нет автоинкрементб есть сиквенс=счетчик, значение которого увеличивается на 1;Sequence
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "groups", cascade = CascadeType.ALL)
//    @ManyToMany(mappedBy = "groups", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    //http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#associations-many-to-many
    public void addUser(User user) {
        users.add( user );
        user.getGroups().add( this );
    }

    public void removeUser(User user) {
        users.remove( user );
        user.getGroups().remove( this );
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}

