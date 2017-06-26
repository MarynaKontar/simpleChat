package com.app.DAO.PooledJdbcDao;


import com.app.BackendException.DatabaseException;
import com.app.DAO.JdbcDao.JdbcUserDao;
import com.app.model.Group;
import com.app.model.Message;
import com.app.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by User on 12.06.2017.
 */
public class PooledJdbcUserDao extends JdbcUserDao {

    private DataSource dataSource;

    public PooledJdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    private static final String READ_SQL = "select login, username, password, registration_date from users where login=?"; //"select * from users where login=?"
    private static final String CREATE_SQL = "insert into users(login, password, username, registration_date) values(?, ?, ?, ?)";
    private static final String UPDATE_SQL = "update users set password=?, username=?, registration_date=? where login=?";
    private static final String DELETE_SQL = "delete from users where login=?";
    private static final String DELETE_FROM_USER_GROUPS = "delete from user_groups where user_login=?";
    private static final String SELECT_GROUPS = "select id, name from groups where id in (select group_id from user_groups where user_login=?) ";
    private static final String ADD_TO_USER_GROUPS = "insert into user_groups(group_id, user_login) values(?,?)";
    private static final String GET_ALL_SQL = "select * from users";
    private static final String DELETE_FROM_MESSAGE = "delete from message where fk_message_user_login=?";
    private static final String CREATE_MESSAGE = "insert into message(user_login, chat_name, text) value(?,?,?)";


//    public void create(User entity) {
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//            connection.setAutoCommit(false);
//
//            AddUserWithoutGroupsAndMessages(entity, connection);
//
//            addUserGroupsToUser_GroupsTable(entity, connection);
//
//            List<Message> messages = createMessagesList(entity);
//
//            addMessagesToMessageTable(connection, messages);
//
//            connection.commit();
//        } catch (Exception e) {
//
//            try {
//                connection.rollback();
//
//            } catch (SQLException e1) {
//                throw new DatabaseException(e1);
//            }
//            throw new DatabaseException(e);
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.setAutoCommit(true);
//                    System.out.println(connection.getAutoCommit());
//                } catch (SQLException e) {
//                    throw new DatabaseException(e);
//                }
//                try {
//                    connection.close();
//                    System.out.println(connection.isClosed());
//                } catch (SQLException e) {
//                    throw new DatabaseException(e);
//                }
//            }
//        }
//    }


    public void createUserWithMessagesExample(User entity) {
        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                AddUserWithoutGroupsAndMessages(entity, connection);

                addUserGroupsToUser_GroupsTable(entity, connection);

                List<Message> messages = createMessagesList(entity);

                addMessagesToMessageTable(connection, messages);

                connection.commit();
                System.out.println(connection.getAutoCommit());

//                throw new SQLException();
                connection.setAutoCommit(true);
                System.out.println(connection.getAutoCommit());

            } catch (Exception e) {//Или  ловить Exception вместо SQLException или вставлять  if (groups != null & !groups.isEmpty()) {} в ddUserGroupsToUser_GroupsTable
                System.out.println("Exception when add user. AutoCommit = " + connection.getAutoCommit());
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException e1) {
                    throw new DatabaseException("Exception when rollback or setAutoCommit. AutoCommit = " + connection.getAutoCommit(), e1);
                }
                throw new DatabaseException(e);
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }


    private void addMessagesToMessageTable(Connection connection, List<Message> messages) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_MESSAGE)) {
            for (Message message : messages) {
                ps.setString(1, message.getUserLogin());
                ps.setString(2, message.getChatName());
                ps.setString(3, message.getText());
                ps.addBatch();
            }
            ps.executeBatch();
//            throw new SQLException();
        }
    }

    private List<Message> createMessagesList(User entity) {
        return IntStream.range(0, 10).mapToObj(i -> {
            Message message = new Message();
            message.setUserLogin(entity.getLogin());
            message.setChatName("Chat0");
            message.setText("Hello this is message  num " + i);
            return message;
        }).collect(Collectors.toList());
    }

    private void AddUserWithoutGroupsAndMessages(User entity, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_SQL)) {
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getUserName());
            ps.setNull(4, Types.TIMESTAMP);
            ps.executeUpdate();
        }
    }


    private void addUserGroupsToUser_GroupsTable(User entity, Connection connection) throws SQLException {
        List<Group> groups = entity.getGroups();
        if (groups != null & !groups.isEmpty()) {
            try (PreparedStatement ps = connection.prepareStatement(ADD_TO_USER_GROUPS)) {
                for (Group group : groups) {
                    ps.setLong(1, group.getId());
                    ps.setString(2, entity.getLogin());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
//        throw new SQLException();
//            throw new NullPointerException();
        }
    }

//
//
//     try (Connection connection = getConnection()){
//        connection.setAutoCommit(false);
//
//        try (PreparedStatement ps = connection.prepareStatement(CREATE_SQL)) {
//            ps.setString(1, entity.getLogin());
//            ps.setString(2, entity.getPassword());
//            ps.setString(3, entity.getUserName());
//            ps.setNull(4, Types.TIMESTAMP);
//            ps.executeUpdate();
//        }
//
//        addUserGroupsToUser_GroupsTable(entity, connection);
//
//        connection.commit();
//    } catch (SQLException e) {
//        throw new DatabaseException(e);
//    }
//
//


}
