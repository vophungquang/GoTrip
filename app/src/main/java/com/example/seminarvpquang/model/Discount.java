package com.example.seminarvpquang.model;

public class Discount {

    private  int id;
    private  String name;
    private  int giadiscount;

    public Discount(int id, String name, int giadiscount) {
        this.id = id;
        this.name = name;
        this.giadiscount = giadiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGiadiscount() {
        return giadiscount;
    }

    public void setGiadiscount(int giadiscount) {
        this.giadiscount = giadiscount;
    }
}
