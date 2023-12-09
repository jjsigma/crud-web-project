package com.tylerpants.webproject.sql;

import com.tylerpants.webproject.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsSQLConnectorOld {
    private final Connection connection;
    private final Statement statement;
    private static final String url = "jdbc:mysql://localhost:3306/web_project_db",
            user = "root",
            password = "root";
    private int userId;

    public ContactsSQLConnectorOld(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.userId = userId;
    }

    public int getContactId(String name, String surname, String phoneNumber) throws SQLException {
        String phoneNumEdited = "+"+phoneNumber.trim();
        String sql = "SELECT id FROM contacts WHERE name = '%s' AND surname = '%s' AND phone_number = '%s'";
        System.out.println("[CONTACT] "+name+" "+surname+" "+phoneNumEdited);
        ResultSet resultSet = statement.executeQuery(String.format(sql, name, surname, phoneNumEdited));
        if(resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }
    public void updateContact(int contactId, String name, String surname, String phoneNumber) throws SQLException {
        statement.executeUpdate(String.format("UPDATE contacts SET name = '%s', surname = '%s', phone_number = '%s' WHERE id = %d", name, surname, phoneNumber, contactId));
    }
    public void deleteContact(int contactId) throws SQLException {
        System.out.println(contactId);
        String sql = "DELETE FROM contacts WHERE id = "+contactId;
        statement.executeUpdate(sql);
    }
}
