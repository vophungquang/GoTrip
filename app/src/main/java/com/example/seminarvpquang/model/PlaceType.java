package com.example.seminarvpquang.model;

public class PlaceType {
    public int id;
    public String namePlace;
    public String imagePlace;

    public PlaceType(int id, String namePlace, String imagePlace) {
        this.id = id;
        this.namePlace = namePlace;
        this.imagePlace = imagePlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnamePlace() {
        return namePlace;
    }

    public void setnamePlace(String namePlace) {
        namePlace = namePlace;
    }

    public String getimagePlace() {
        return imagePlace;
    }

    public void setimagePlace(String imagePlace) {
        imagePlace = imagePlace;
    }
}
