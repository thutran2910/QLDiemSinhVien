/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.pojo;

public class DiemTrungBinh {
    private String sinhVienName;
    private float diemTrungBinh;

    public DiemTrungBinh(String sinhVienName, float diemTrungBinh) {
        this.sinhVienName = sinhVienName;
        this.diemTrungBinh = diemTrungBinh;
    }

    public String getSinhVienName() {
        return sinhVienName;
    }

    public void setSinhVienName(String sinhVienName) {
        this.sinhVienName = sinhVienName;
    }

    public float getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(float diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }
}
