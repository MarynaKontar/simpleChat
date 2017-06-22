package com.app.DAO;

import com.app.model.Group;

/**
 * Created by User on 14.06.2017.
 */
public interface DAOGroup extends DAO<Long, Group> {

    void deleteByKey(Long key);
}
