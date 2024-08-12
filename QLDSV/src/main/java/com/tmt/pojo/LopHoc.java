/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "lophoc")
public class LopHoc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "idNganhDaoTao")
    private NganhDaoTao nganhDaoTao;

    @OneToMany(mappedBy = "lopHoc", fetch = FetchType.LAZY)
    private Set<SinhVien> sinhViens;


    // Constructors, Getters and Setters
    public LopHoc() {}

    public LopHoc(Integer id, String name, NganhDaoTao nganhDaoTao) {
        this.id = id;
        this.name = name;
        this.nganhDaoTao = nganhDaoTao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NganhDaoTao getNganhDaoTao() {
        return nganhDaoTao;
    }

    public void setNganhDaoTao(NganhDaoTao nganhDaoTao) {
        this.nganhDaoTao = nganhDaoTao;
    }

    public Set<SinhVien> getSinhViens() {
        return sinhViens;
    }

    public void setSinhViens(Set<SinhVien> sinhViens) {
        this.sinhViens = sinhViens;
    }

    
}
