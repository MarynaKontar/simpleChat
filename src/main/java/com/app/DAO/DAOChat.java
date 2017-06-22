package com.app.DAO;

import com.app.model.Chat;

/**
 * Created by User on 09.06.2017.
 */
public interface DAOChat extends DAO<String, Chat>{

    void deleteByKey(String key);
}
