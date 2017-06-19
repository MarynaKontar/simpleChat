package com.app.DAO;

import com.app.Model.Message;

/**
 * Created by User on 09.06.2017.
 */
public interface DAOMessage extends DAO<Long, Message>{

    void deleteByKey(long key);
}
