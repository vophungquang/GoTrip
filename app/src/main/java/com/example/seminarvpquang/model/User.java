package com.example.seminarvpquang.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String  email ;
    private String address;

    public User(int id, String username, String email, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

