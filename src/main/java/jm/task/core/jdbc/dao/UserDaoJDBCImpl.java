package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USERNAME, Util.PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = """
                    CREATE TABLE IF NOT EXISTS postgres (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(50),
                    surname VARCHAR(50),
                    age SMALLINT)""";
            statement.executeUpdate(sql);


        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USERNAME, Util.PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = """
                    DROP TABLE IF EXISTS postgres""";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection collection = DriverManager.getConnection(Util.URL, Util.USERNAME, Util.PASSWORD);
             PreparedStatement preparedStatement = collection.prepareStatement(
                     "INSERT INTO postgres(name,surname,age) values (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection collection = DriverManager.getConnection(Util.URL, Util.USERNAME, Util.PASSWORD);
             PreparedStatement preparedStatement = collection.prepareStatement("DELETE FROM postgres WHERE id = ?")) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USERNAME, Util.PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM postgres");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getByte("age"));
                list.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection collection = DriverManager.getConnection(Util.URL, Util.USERNAME, Util.PASSWORD);
             Statement statement = collection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}



