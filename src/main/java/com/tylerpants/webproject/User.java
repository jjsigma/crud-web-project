package com.tylerpants.webproject;

import java.util.Objects;

public class User {
    private final String username;
    private final String password;
    private final int id;

    public String getName() {
        return username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = generateId(username, password);
    }

    public User(int id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    private int generateId(String username, String password) {
        return Math.abs(37 * Objects.hash(username, password));
    }
}
