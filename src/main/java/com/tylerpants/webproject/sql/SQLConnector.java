package com.tylerpants.webproject.sql;

import com.tylerpants.webproject.Contact;

import java.util.List;

public class SQLConnector {
    private int userId;
    public SQLConnector(int userId) {
        this.userId = userId;
    }
    public List<Contact> getContacts(int userId) {
        return null;
    }
}
