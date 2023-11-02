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
    private int userId;

    public ContactsSQLConnector(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.userId = userId;
    }
    public List<Contact> getContactsById() {
        ResultSet resultSet;
        List<Contact> contacts = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM contacts WHERE user_id ="+this.userId);
            while (resultSet.next()) {
                Contact contact = new Contact(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("phone_number"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return !contacts.isEmpty() ? contacts : null;
    }
    public void addContact(String name, String surname, String phoneNumber) throws SQLException {
        statement.executeUpdate(String.format("INSERT INTO contacts(name, surname, phone_number, user_id) VALUES ('%s', '%s', '%s', %d)",
                name, surname, phoneNumber, this.userId));
    }
}
