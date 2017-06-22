package com.app.HibernateModel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by User on 12.06.2017.
 */
@Embeddable
@Table(name = "groups")
public class Group {
@Id
@Column(name = "id")
    private long id;
@Column(name = "name")
    private String name;

    public Group() {
    }

    public Group(long id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Group{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

