package com.example.seminarvpquang.model;

public class Comment {
    int id ;
    String username;;
    String tensanpham;
    String content;

    public Comment(int id, String username, String tensanpham, String content) {
        this.id = id;
        this.username = username;
        this.tensanpham = tensanpham;
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

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

