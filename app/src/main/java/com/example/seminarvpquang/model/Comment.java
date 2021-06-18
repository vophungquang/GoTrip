package com.example.seminarvpquang.model;

public class Comment {
    int id ;
    String username;;
    String tendiadiem;
    String content;

    public Comment(int id, String username, String tendiadiem, String content) {
        this.id = id;
        this.username = username;
        this.tendiadiem = tendiadiem;
        this.content = content;
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

    public String gettendiadiem() {
        return tendiadiem;
    }

    public void settendiadiem(String tendiadiem) {
        this.tendiadiem = tendiadiem;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

