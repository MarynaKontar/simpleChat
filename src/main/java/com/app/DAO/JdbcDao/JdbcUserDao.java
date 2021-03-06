package com.app.DAO.JdbcDao;

import com.app.BackendException.DatabaseException;
import com.app.DAO.DAOUser;
import com.app.model.Group;
import com.app.model.User;

import java.sql.*;
import java.util.*;

/**
 * Created by User on 09.06.2017.
 */
public class JdbcUserDao implements DAOUser {

    private static final String READ_SQL = "select login, username, password, registration_date from users where login=?"; //"select * from users where login=?"
    private static final String CREATE_SQL = "insert into users(login, password, username, registration_date) values(?, ?, ?, ?)";
    private static final String UPDATE_SQL = "update users set password=?, username=?, registration_date=? where login=?";
    private static final String DELETE_SQL = "delete from users where login=?";
    private static final String DELETE_FROM_USER_GROUPS = "delete from user_groups where user_login=?";
    private static final String SELECT_GROUPS = "select id, name from groups where id in (select group_id from user_groups where user_login=?) ";
    private static final String ADD_TO_USER_GROUPS = "insert into user_groups(group_id, user_login) values(?,?)";
    private static final String GET_ALL_SQL = "select * from users";
    private static final String DELETE_FROM_MESSAGE = "delete from message where user_login=?";


    @Override
    public void create(User entity) {

        try (Connection connection = getConnection()) {
            try {
                connection.setAutoCommit(false);

                try (PreparedStatement ps = connection.prepareStatement(CREATE_SQL)) {
                    ps.setString(1, entity.getLogin());
                    ps.setString(2, entity.getPassword());
                    ps.setString(3, entity.getUserName());
                    ps.setNull(4, Types.TIMESTAMP);
                    ps.executeUpdate();
                }

                addUserGroupsToUser_Groups(entity, connection);

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

    private void addUserGroupsToUser_Groups(User entity, Connection connection) throws SQLException {
        Set<Group> groups = entity.getGroups();
        if (groups != null & !groups.isEmpty()) {
            try (PreparedStatement ps = connection.prepareStatement(ADD_TO_USER_GROUPS)) {
                for (Group group : groups) {
                    ps.setLong(1, group.getId());
                    ps.setString(2, entity.getLogin());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    @Override
    public Optional<User> read(String key) { //key = login

        try (Connection connection = getConnection()) {
            User user = null;
            try (PreparedStatement ps = connection.prepareStatement(READ_SQL)) {
                ps.setString(1, key);
                try (ResultSet result = ps.executeQuery()) {
                    if (!result.next()) return Optional.empty();
                    user = new User();
                    user.setLogin(result.getString("login"));
                    user.setUserName(result.getString("username"));
                    user.setPassword(result.getString("password"));
                    user.setRegistrationDate(result.getTimestamp("registration_date"));
                }
            }

            Set<Group> groups = getGroupsByKey(key, connection);
            user.setGroups(groups);
            return Optional.of(user);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Set<Group> getGroupsByKey(String key, Connection connection) throws SQLException {
        Set<Group> groups = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_GROUPS)) {
            ps.setString(1, key);
            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    Group group = new Group();
                    group.setId(result.getLong("id"));
                    group.setName(result.getString("name"));
                    groups.add(group);
                }
            }
        }
        return groups;
    }


    @Override
    public void update(User entity) { //изменить login мы user не позволяем? Для этого случая надо написать другой метод

        try (Connection connection = getConnection()) {
            try {
                connection.setAutoCommit(false);

                try (PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
                    ps.setString(1, entity.getPassword());
                    ps.setString(2, entity.getUserName());
                    ps.setNull(3, Types.TIMESTAMP);
                    ps.setString(4, entity.getLogin());
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = connection.prepareStatement(DELETE_FROM_USER_GROUPS)) {
                    ps.setString(1, entity.getLogin());
                    ps.executeUpdate();
                }

                addUserGroupsToUser_Groups(entity, connection);

                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException e1) {
                    throw new DatabaseException(e1);
                }
                throw new DatabaseException(e);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(User entity) {
        String key = entity.getLogin();
        deleteByKey(key);
    }

    @Override
    public Set<User> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_SQL);
             ResultSet result = ps.executeQuery()) {
            Set<User> users = new HashSet<>();

            while (result.next()) {
                User user = new User();
                String login = result.getString("login");
                user.setLogin(login);
                user.setPassword(result.getString("password"));
                user.setUserName(result.getString("username"));
                user.setRegistrationDate(result.getTimestamp("registration_date"));

                Set<Group> groups = getGroupsByKey(login, connection);
                user.setGroups(groups);

                users.add(user);
            }
            return users;

        } catch (Exception e) {  //ловлю Exception, а не SQLException потому что .add может кидать много разных Exception,
            // но пользователю это не надо. Ему главное знать, что к БД не удалось обратиться.
            throw new DatabaseException(e);
        }
    }

    @Override
    public void deleteByKey(String key) {  //key = login

        try (Connection connection = getConnection()) {
            try {
                connection.setAutoCommit(false);

                try (PreparedStatement ps = connection.prepareStatement(DELETE_FROM_MESSAGE)) {
                    ps.setString(1, key);
                    ps.executeUpdate();
                }
                try (PreparedStatement ps = connection.prepareStatement(DELETE_FROM_USER_GROUPS)) {
                    ps.setString(1, key);
                    ps.executeUpdate();
                }
                try (PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
                    ps.setString(1, key);
                    ps.executeUpdate();
                }
//                throw new SQLException();

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