package com.app.DAO.JdbcDao;

import com.app.BackendException.DatabaseException;
import com.app.DAO.DAOGroup;
import com.app.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 14.06.2017.
 */
public class JdbcGroupsDao implements DAOGroup {

    //сделать рефакторинг - сделать константы для названий колонок в таблице

    private static final String CREATE_SQL = "insert into groups(`name`) value(?)";
    private static final String READ_SQL = "select id, `name` from groups where id=?";
    private static final String UPDATE_SQL = "update groups set name=? where id=?";
    private static final String DELETE_SQL = "delete from groups where id=?";
    private static final String GET_ALL_SQL = "select * from groups";
    private static final String DELETE_FROM_USER_GROUPS = "delete from user_groups where group_id=?";


    @Override
    public void create(Group entity) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(CREATE_SQL)) {
                ps.setString(1, entity.getName());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Group> read(Long key) {
        try (Connection connection = getConnection()) {
            Group group = null;
            try (PreparedStatement ps = connection.prepareStatement(READ_SQL)) {
                ps.setLong(1, key);
                try (ResultSet result = ps.executeQuery()) {
                    if (!result.next()) return Optional.empty();
                    group = new Group();
                    group.setId(result.getLong("id"));
                    group.setName(result.getString("name"));
                }
            }
            return Optional.of(group);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(Group entity) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, entity.getName());
            ps.setLong(1, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(Group entity) {
        Long key = entity.getId();
        deleteByKey(key);
    }

    @Override
    public List<Group> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_SQL);
             ResultSet result = ps.executeQuery()) {
            List<Group> groups = new ArrayList<>();
            while (result.next()) {
                Group group = new Group();
                group.setId(result.getLong("id"));
                group.setName(result.getString("name"));
                groups.add(group);
            }
            return groups;
        } catch (Exception e) {  //ловлю Exception, а не SQLException потому что .add может кидать много разных Exception,
            // но пользователю это не надо. Ему главное знать, что к БД не удалось обратиться.
            throw new DatabaseException(e);
        }
    }

    @Override
    public void deleteByKey(Long key) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(DELETE_FROM_USER_GROUPS)) {
                ps.setLong(1, key);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
                ps.setLong(1, key);
                ps.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return JdbcConnectionToDB.getConnection();
    }
}
