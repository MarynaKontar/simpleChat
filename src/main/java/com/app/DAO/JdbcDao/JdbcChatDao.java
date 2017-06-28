package com.app.DAO.JdbcDao;

import com.app.BackendException.DatabaseException;
import com.app.DAO.DAOChat;
import com.app.model.Chat;

import java.sql.*;
import java.util.*;

/**
 * Created by User on 14.06.2017.
 */
public class JdbcChatDao implements DAOChat {

    //сделать рефакторинг - сделать константы для названий колонок в таблице

    private static final String CREATE_SQL = "insert into chat(`name`, description) values(?,?)";
    private static final String READ_SQL = "select `name`, description from chat where `name`=?";
    private static final String UPDATE_SQL = "update chat set description=? where `name`=?";
    private static final String DELETE_SQL = "delete from chat where `name`=?";
    private static final String GET_ALL_SQL = "select * from chat";
    private static final String DELETE_FROM_MESSAGE = "delete from message where chat_name=?";

    @Override
    public void create(Chat entity) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(CREATE_SQL)) {
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getDescription());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Chat> read(String key) { //key = name
        try (Connection connection = getConnection()) {
            Chat chat = null;
            try (PreparedStatement ps = connection.prepareStatement(READ_SQL)) {
                ps.setString(1, key);
                try (ResultSet result = ps.executeQuery()) {
                    if (!result.next()) return Optional.empty();
                    chat = new Chat();
                    chat.setName(result.getString("name"));
                    chat.setDescription(result.getString("description"));
                }
            }
            return Optional.of(chat);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(Chat entity) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, entity.getDescription());
            ps.setString(2, entity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(Chat entity) {
        String key = entity.getName();
        deleteByKey(key);

    }

    @Override
    public Set<Chat> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_SQL);
             ResultSet result = ps.executeQuery()) {
           Set<Chat> chats = new HashSet<>();

            while (result.next()) {
                Chat chat = new Chat();
                chat.setName(result.getString("name"));
                chat.setDescription(result.getString("description"));
                chats.add(chat);
            }
            return chats;
        } catch (Exception e) {  //ловлю Exception, а не SQLException потому что .add может кидать много разных Exception,
            // но пользователю это не надо. Ему главное знать, что к БД не удалось обратиться.
            throw new DatabaseException(e);
        }
    }

    @Override
    public void deleteByKey(String key) {  //key = name

        try (Connection connection = getConnection()) {
            try {
                connection.setAutoCommit(false);

                try (PreparedStatement ps = connection.prepareStatement(DELETE_FROM_MESSAGE)) {
                    ps.setString(1, key);
                    ps.executeUpdate();
                }
                try (PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
                    ps.setString(1, key);
                    ps.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException e1) {
                    throw new DatabaseException("Exception when rollback or setAutoCommit. AutoCommit = " + connection.getAutoCommit(), e1);
                }
                throw new DatabaseException(e);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return JdbcConnectionToDB.getConnection();
    }

}
