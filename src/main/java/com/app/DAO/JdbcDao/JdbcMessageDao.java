package com.app.DAO.JdbcDao;

import com.app.BackendException.DatabaseException;
import com.app.DAO.DAOMessage;
import com.app.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 15.06.2017.
 */
public class JdbcMessageDao implements DAOMessage {

    //сделать рефакторинг - сделать константы для названий колонок в таблице

    private static final String CREATE_SQL = "insert into message(fk_message_user_login, fk_message_chat_name, text) " +
            "value(?,?,?)";
    private static final String READ_SQL = "select * from message where id=?";
    private static final String UPDATE_SQL = "update message set text=? where id=?";
    private static final String DELETE_SQL = "delete from message where id=?";
    private static final String GET_ALL_SQL = "select * from message";


    @Override
    public void create(Message entity) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_SQL)) {
            ps.setString(1, entity.getUserLogin());
            ps.setString(2, entity.getChatName());
            ps.setString(3, entity.getText());
            ps.setNull(4, Types.TIMESTAMP);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Message> read(Long key) {

        try (Connection connection = getConnection()) {
            Message message = null;
            try (PreparedStatement ps = connection.prepareStatement(READ_SQL)) {
                ps.setLong(1, key);
                try (ResultSet result = ps.executeQuery()) {
                    if (!result.next()) return Optional.empty();
                    message = new Message();
                    message.setId(result.getLong("message_id"));
                    message.setUserLogin(result.getString("fk_message_user_login"));
                    message.setChatName(result.getString("fk_message_chat_name"));
                    message.setText(result.getString("text"));
                    message.setMessageDate(result.getTimestamp("date"));
                }
            }
            return Optional.of(message);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(Message entity) {

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, entity.getText());
            ps.setLong(1, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(Message entity) {
        Long key = entity.getId();
        deleteByKey(key);
    }

    @Override
    public List<Message> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_SQL);
             ResultSet result = ps.executeQuery()) {
            List<Message> messages = new ArrayList<>();

            while (result.next()) {
                Message message = new Message();
                message.setId(result.getInt("message_id"));
                message.setUserLogin(result.getString("fk_message_user_login"));
                message.setChatName(result.getString("fk_message_chat_name"));
                message.setText(result.getString("text"));
                message.setMessageDate(result.getTimestamp("date"));
                messages.add(message);
            }
            return messages;
        } catch (Exception e) {  //ловлю Exception, а не SQLException потому что .add может кидать много разных Exception,
            // но пользователю это не надо. Ему главное знать, что к БД не удалось обратиться.
            throw new DatabaseException(e);
        }
    }

    @Override
    public void deleteByKey(long key) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
                ps.setLong(1, key);
                ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return JdbcConnectionToDB.getConnection();
    }
}
