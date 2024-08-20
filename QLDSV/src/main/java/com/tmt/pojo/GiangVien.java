/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "giangvien")
public class GiangVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private NguoiDung nguoiDung;

    @OneToMany(mappedBy = "giangVien")
    private Set<GiangVienLopHoc> giangVienLopHocs = new HashSet<>();

    // Getters and Setters

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

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public Set<GiangVienLopHoc> getGiangVienLopHocs() {
        return giangVienLopHocs;
    }

    public void setGiangVienLopHocs(Set<GiangVienLopHoc> giangVienLopHocs) {
        this.giangVienLopHocs = giangVienLopHocs;
    }
}
