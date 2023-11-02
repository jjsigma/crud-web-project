package com.tylerpants.webproject.sql;

import com.tylerpants.webproject.Contact;
import com.tylerpants.webproject.User;

import java.sql.*;
import java.util.List;

public class UserSQLConnector {
    private final Connection connection;
    private final Statement statement;
    private int userId;
    private static final String url = "jdbc:mysql://localhost:3306/web_project_db",
            user = "root",
            password = "root";

    public UserSQLConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserId(String username) throws SQLException {
        ResultSet resultSet = getUserResultSet(username);

        if(!resultSet.next()) return -1;
        this.userId = resultSet.getInt("id");
        return this.userId;
    }
    public boolean checkIfUsernameExists(String username) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE username = '%s'", username));
        return resultSet.next();
    }
    public void createUser(User user) throws SQLException {
        statement.executeUpdate(String.format("INSERT INTO users(id, username, password) VALUES(%d, '%s', '%s')",user.getId(), user.getName(), user.getPassword()));
    }
    private ResultSet getUserResultSet(String username) throws SQLException {
        return statement.executeQuery("SELECT * FROM users WHERE username = '"+ username+"'");
    }
    public User getUser(String username) throws SQLException {
        ResultSet resultSet = getUserResultSet(username);
        return new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
    }
}
