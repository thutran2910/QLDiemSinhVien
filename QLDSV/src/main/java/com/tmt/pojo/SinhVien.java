/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sinhvien")
public class SinhVien extends NguoiDung implements Serializable {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "idLopHoc")
    private LopHoc lopHoc;

    @ManyToOne
    @JoinColumn(name = "idKhoa")
    private Khoa khoa;

    @ManyToOne
    @JoinColumn(name = "idNganhDaoTao")
    private NganhDaoTao nganhDaoTao;

    @NotNull
    @Email(message = "Email không đúng định dạng")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@ou\\.edu\\.vn$", message = "Email phải có đuôi @ou.edu.vn")
    @Column(nullable = false, unique = true)
    private String email;

   @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
     @Column(name = "ngaySinh", nullable = false)
    private Date ngaySinh;

    @NotNull
    @Size(max = 100)
    @Column(name = "queQuan")
    private String queQuan;

    @NotNull
    @Size(max = 10)
    @Column(nullable = false)
    private String gioiTinh;  // Changed to String

    public SinhVien() {
    }

    public SinhVien(Integer id) {
        super(id);
    }

    public SinhVien(Integer id, String name, LopHoc lopHoc, Khoa khoa, NganhDaoTao nganhDaoTao, String email, Date ngaySinh, String queQuan, String gioiTinh) {
        super(id);
        this.name = name;
        this.lopHoc = lopHoc;
        this.khoa = khoa;
        this.nganhDaoTao = nganhDaoTao;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.gioiTinh = gioiTinh;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LopHoc getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(LopHoc lopHoc) {
        this.lopHoc = lopHoc;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public NganhDaoTao getNganhDaoTao() {
        return nganhDaoTao;
    }

    public void setNganhDaoTao(NganhDaoTao nganhDaoTao) {
        this.nganhDaoTao = nganhDaoTao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}