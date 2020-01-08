package com.inno.dao;

import com.inno.ConnectionManager.ConnectionManager;
import com.inno.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@EJB
public class UserDaoJdbcImpl implements DataDao<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoJdbcImpl.class);
    public static final String INSERT_INTO_USER = "INSERT INTO mobile.public.user values (DEFAULT, ?, ?, ?, ?)";
    public static final String SELECT_FROM_USER = "SELECT * FROM mobile.public.user WHERE login = ? and password = ?";
    public static final String SELECT_ALL_FROM_USER = "SELECT * FROM mobile.public.user";
    public static final String UPDATE_USER = "UPDATE mobile.public.user SET login=?, email=?, phone=?, password=? WHERE id=?";
    public static final String DELETE_FROM_USER = "DELETE FROM mobile.public.user WHERE id=?";

    private ConnectionManager connectionManager;

    @Inject
    public UserDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean add(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in addUser method", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public Optional<User> get(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.ofNullable(new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in getUserByLoginPassword method", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void createTable() {

    }
}
