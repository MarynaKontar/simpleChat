package com.app.DAO;


import com.app.model.User;

/**
 * Created by User on 09.06.2017.
 */
public interface DAOUser extends DAO<String, User>{

    void deleteByKey(String key);
}
