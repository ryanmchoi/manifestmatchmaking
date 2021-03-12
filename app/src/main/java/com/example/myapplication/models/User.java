package com.example.myapplication.models;

public class User {
    private String username;
    private String password;
    private int isDACO;

    public User() {

    }

    public User(String username, String password, int isDACO) {
        this.username = username;
        this.password = password;
        this.isDACO = isDACO;
    }

    public String getUsername() {
        return username;
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

    public int getIsDACO() {
        return isDACO;
    }

    public void setIsDACO(int isDACO) {
        this.isDACO = isDACO;
    }
    
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isDACO='" + isDACO + '\'' +
                '}';
    }
}
