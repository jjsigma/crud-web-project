package com.tylerpants.webproject;

public class User {
    private String username;
    private String password;
    private int id;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
    private int generateId(String username, String password) {
        return (int) (37 * username.hashCode() + (Math.random() * 10) * password.hashCode());
    }
}
