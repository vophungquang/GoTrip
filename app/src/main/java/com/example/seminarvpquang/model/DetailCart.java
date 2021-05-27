package com.example.seminarvpquang.model;

import java.io.Serializable;

public class DetailCart implements Serializable {
    private int id;
    private int idUser;
    private String tensanpham;
    private int giasanpham;
    private int soluong;
    private  String ngaymuahang;
    private  String hinhanhsanpham;
    private String diachigiaohang;

    public DetailCart(int id, int idUser, String tensanpham, int giasanpham,int soluong, String ngaymuahang, String diachigiaohang,String hinhanhsanpham) {
        this.id = id;
        this.idUser = idUser;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.soluong=soluong;
        this.ngaymuahang = ngaymuahang;
        this.diachigiaohang = diachigiaohang;
        this.hinhanhsanpham=hinhanhsanpham;
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

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getNgaymuahang() {
        return ngaymuahang;
    }

    public void setNgaymuahang(String ngaymuahang) {
        this.ngaymuahang = ngaymuahang;
    }

    public String getDiachigiaohang() {
        return diachigiaohang;
    }

    public void setDiachigiaohang(String diachigiaohang) {
        this.diachigiaohang = diachigiaohang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }
}

