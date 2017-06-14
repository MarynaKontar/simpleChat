package com.app.DAO;

import com.app.BackendException.DatabaseException;
import com.app.Model.Group;
import com.app.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 09.06.2017.
 */
public class JdbcUserDao implements DAOUser {

    private static final String READ_SQL = "select login, username, password, registration_date from users where login=?"; //"select * from users where login=?"
    private static final String CREATE_SQL = "insert into users(login, password, username, registration_date) values(?, ?, ?, ?)";
    private static final String UPDATE_SQL = "update users set password=?, username=?, registration_date=? where login=?";
    private static final String DELETE_SQL = "delete from users where login=?";
    private static final String SELECT_GROUPS = "select id, name from groups where id in (select group_id from user_groups where user_login=?) ";


    @Override
    public void create(User entity) {

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(CREATE_SQL)) {
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getUserName());
            ps.setNull(4, Types.TIMESTAMP);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }


    }

    @Override
    public Optional<User> read(String key) { //login

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

            List<Group> groups = getGroupsByKey(key, connection);
            user.setGroups(groups);
            return Optional.ofNullable(user);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private List<Group> getGroupsByKey(String key, Connection connection) throws SQLException {
        List<Group> groups = new ArrayList<>();
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
    public void update(User entity) {

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, entity.getPassword());
            ps.setString(2, entity.getUserName());
            ps.setNull(3, Types.TIMESTAMP);
            ps.setString(4, entity.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(User entity) {
        try(Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setString(1, entity.getLogin());


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }


    }

    @Override
    public List<User> getAll() {
        return null;
    }


    protected Connection getConnection() throws SQLException {
        return JdbcConnectionToDB.getConnection();
    }
}