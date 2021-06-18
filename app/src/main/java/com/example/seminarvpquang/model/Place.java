package com.example.seminarvpquang.model;

import java.io.Serializable;

public class Place implements Serializable {
    public int id;
    public String namePlace;
    public Integer votePlace;
    public String imagePlace;
    public String descriptionPlace;
    public int idPlace;
    public int doanhthu;
    public int mota;
    public String diemdanhgia;

    public Place(int id, String namePlace, Integer votePlace, String imagePlace, String descriptionPlace, int idPlace) {
        this.id = id;
        this.namePlace = namePlace;
        this.votePlace =votePlace;
        this.imagePlace = imagePlace;
        this.descriptionPlace = descriptionPlace;
        this.idPlace = idPlace;
    }

    public Place(int id, String s, String getimagePlace, String getdescriptionPlace, int i, int getdoanhthu, String diemdanhgia) {
    }

    public Place(int id, String namePlace, Integer votePlace, String imagePlace, String descriptionPlace, int idPlace, int id_thuonghieu, int doanhthu, String diemdanhgia) {
        this.id = id;
        this.namePlace = namePlace;
        this.votePlace =votePlace;
        this.imagePlace = imagePlace;
        this.descriptionPlace = descriptionPlace;
        this.idPlace = idPlace;
        this.doanhthu = doanhthu;
        this.mota = mota;
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

    public Integer getPriceProduct() {
        return votePlace;
    }

    public void setPriceProduct(Integer votePlace) {
        this.votePlace =votePlace;
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

    public int getmota() {
        return mota;
    }

    public void setmota(int mota) {
        this.mota = mota;
    }

    public String getDiemdanhgia() {
        return diemdanhgia;
    }

    public void setDiemdanhgia(String diemdanhgia) {
        this.diemdanhgia = diemdanhgia;
    }
}

