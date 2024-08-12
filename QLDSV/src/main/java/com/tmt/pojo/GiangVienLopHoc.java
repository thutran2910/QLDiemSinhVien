/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(GiangVienLopHocId.class)
@Table(name = "giangvien_lophoc")
public class GiangVienLopHoc implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idGiangVien")
    private GiangVien giangVien;

    @Id
    @ManyToOne
    @JoinColumn(name = "idLopHoc")
    private LopHoc lopHoc;

    // Getters and Setters

    public GiangVien getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }
}
