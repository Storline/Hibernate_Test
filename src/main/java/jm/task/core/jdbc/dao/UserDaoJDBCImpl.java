package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {

        String createTable = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, last_name VARCHAR(60) NOT NULL, age TINYINT(3) NOT NULL)";

        try (Connection connection = util.getConnection(); Statement statement = connection.prepareStatement(createTable)) {
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS user";

        try (Connection connection = util.getConnection(); Statement statement = connection.prepareStatement(dropTable)) {
            statement.executeUpdate(dropTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String addUser = "INSERT INTO user (name, last_name, age) VALUES (?, ?, ?)";

        try (Connection connection = util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(addUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeById = "DELETE FROM user WHERE id = ?";

        try (Connection connection = util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(removeById)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String getUsers = "SELECT * FROM user";
        User user = new User();

        try (Connection connection = util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getUsers);

            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
                System.out.println(user.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE user";

        try (Connection connection = util.getConnection(); Statement statement = connection.prepareStatement(cleanTable)) {
            statement.executeUpdate(cleanTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
