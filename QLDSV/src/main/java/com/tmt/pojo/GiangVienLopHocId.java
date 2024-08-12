/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.pojo;

import java.io.Serializable;
import java.util.Objects;

public class GiangVienLopHocId implements Serializable {

    private int giangVien;
    private int lopHoc;

    // Getters, Setters, hashCode, equals

    public int getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(int giangVien) {
        this.giangVien = giangVien;
    }

    public int getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(int lopHoc) {
        this.lopHoc = lopHoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiangVienLopHocId that = (GiangVienLopHocId) o;
        return giangVien == that.giangVien && lopHoc == that.lopHoc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(giangVien, lopHoc);
    }
}
