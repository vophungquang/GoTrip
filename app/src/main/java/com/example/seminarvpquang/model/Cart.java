package com.example.seminarvpquang.model;

import java.io.Serializable;

public class Cart implements Serializable {
    private int id;

    private int idUser;
    private String tensanpham;
    private int soluong;
    private String ngaymuahang;
    private int giasanpham;
    private String tenhuonghieu;
    private String imageSanPham;
    private int sosanphamtonkho;

    public Cart(int id, int idUser, String tensanpham, int soluong, String ngaymuahang, int giasanpham, String imageSanPham) {
        this.id = id;
        this.idUser = idUser;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.ngaymuahang = ngaymuahang;
        this.giasanpham = giasanpham;
        this.imageSanPham = imageSanPham;
    }

    public Cart(int id, int idUser, String tensanpham, int soluong, String ngaymuahang, int giasanpham, String tenhuonghieu, int sosanphamtonkho, String imageSanPham) {
        this.id = id;
        this.idUser = idUser;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.ngaymuahang = ngaymuahang;
        this.giasanpham = giasanpham;
        this.tenhuonghieu=tenhuonghieu;
        this.imageSanPham=imageSanPham;
        this.sosanphamtonkho=sosanphamtonkho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getNgaymuahang() {
        return ngaymuahang;
    }

    public void setNgaymuahang(String ngaymuahang) {
        this.ngaymuahang = ngaymuahang;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getTenhuonghieu() {
        return tenhuonghieu;
    }

    public void setTenhuonghieu(String tenhuonghieu) {
        this.tenhuonghieu = tenhuonghieu;
    }

    public String getImageSanPham() {
        return imageSanPham;
    }

    public void setImageSanPham(String imageSanPham) {
        this.imageSanPham = imageSanPham;
    }

    public int getSosanphamtonkho() {
        return sosanphamtonkho;
    }

    public void setSosanphamtonkho(int sosanphamtonkho) {
        this.sosanphamtonkho = sosanphamtonkho;
    }
}
