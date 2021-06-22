package com.example.seminarvpquang.model;

import java.io.Serializable;

public class Place implements Serializable {
    public int id;
    public String namePlace;
    public String imagePlace;
    public String descriptionPlace;
    public int idPlace;
    public int doanhthu;
    public String diemdanhgia;

    public Place(int id, String namePlace, String imagePlace, String descriptionPlace, int idPlace, int doanhthu, String diemdanhgia) {
        this.id = id;
        this.namePlace = namePlace;
        this.imagePlace = imagePlace;
        this.descriptionPlace = descriptionPlace;
        this.idPlace = idPlace;
        this.doanhthu = doanhthu;
        this.diemdanhgia = diemdanhgia;
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
        this.namePlace = namePlace;
    }

    public String getimagePlace() {
        return imagePlace;
    }

    public void setimagePlace(String imagePlace) {
        this.imagePlace = imagePlace;
    }

    public String getdescriptionPlace() {
        return descriptionPlace;
    }

    public void setdescriptionPlace(String descriptionPlace) {
        this.descriptionPlace = descriptionPlace;
    }

    public int getidPlace() {
        return idPlace;
    }

    public void setidPlace(int idPlace) {
        this.idPlace = idPlace;
    }


    public int getdoanhthu() {
        return doanhthu;
    }

    public void setdoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }

    public String getDiemdanhgia() {
        return diemdanhgia;
    }

    public void setDiemdanhgia(String diemdanhgia) {
        this.diemdanhgia = diemdanhgia;
    }
}

