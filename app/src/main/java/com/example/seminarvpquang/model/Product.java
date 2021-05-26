package com.example.seminarvpquang.model;

import java.io.Serializable;

public class Product implements Serializable {
    public int id;
    public String nameProduct;
    public Integer priceProduct;
    public String imageProduct;
    public String descriptionProduct;
    public int idProduct;
    public int id_thuonghieu;
    public int sosanphamdaban;
    public int sosanphamcontonkho;
    public String diemdanhgia;

    public Product(int id, String nameProduct, Integer priceProduct, String imageProduct, String descriptionProduct, int idProduct) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.imageProduct = imageProduct;
        this.descriptionProduct = descriptionProduct;
        this.idProduct = idProduct;
    }

    public Product() {
    }

    public Product(int id, String nameProduct, Integer priceProduct, String imageProduct, String descriptionProduct, int idProduct, int id_thuonghieu, int sosanphamdaban, int sosanphamcontonkho, String diemdanhgia) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.imageProduct = imageProduct;
        this.descriptionProduct = descriptionProduct;
        this.idProduct = idProduct;
        this.id_thuonghieu = id_thuonghieu;
        this.sosanphamdaban = sosanphamdaban;
        this.sosanphamcontonkho = sosanphamcontonkho;
        this.diemdanhgia = diemdanhgia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Integer getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Integer priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getId_thuonghieu() {
        return id_thuonghieu;
    }

    public void setId_thuonghieu(int id_thuonghieu) {
        this.id_thuonghieu = id_thuonghieu;
    }

    public int getSosanphamdaban() {
        return sosanphamdaban;
    }

    public void setSosanphamdaban(int sosanphamdaban) {
        this.sosanphamdaban = sosanphamdaban;
    }

    public int getSosanphamcontonkho() {
        return sosanphamcontonkho;
    }

    public void setSosanphamcontonkho(int sosanphamcontonkho) {
        this.sosanphamcontonkho = sosanphamcontonkho;
    }

    public String getDiemdanhgia() {
        return diemdanhgia;
    }

    public void setDiemdanhgia(String diemdanhgia) {
        this.diemdanhgia = diemdanhgia;
    }
}

