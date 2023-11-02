package com.tylerpants.webproject.sql;

import com.tylerpants.webproject.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsSQLConnector {
    private final Connection connection;
    private final Statement statement;
    private static final String url = "jdbc:mysql://localhost:3306/web_project_db",
            user = "root",
            password = "root";

    public ContactsSQLConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Contact> getContactsById(int userId) {
        ResultSet resultSet;
        List<Contact> contacts = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM contacts WHERE user_id ="+userId);
            while (resultSet.next()) {
                Contact contact = new Contact(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("phone_number"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return !contacts.isEmpty() ? contacts : null;
    }

}
