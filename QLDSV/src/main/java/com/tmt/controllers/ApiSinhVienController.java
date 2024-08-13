/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.controllers;

import com.tmt.pojo.SinhVien;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.Khoa;
import com.tmt.pojo.NganhDaoTao;
import com.tmt.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sinhvien")
public class ApiSinhVienController {

    private final SinhVienService sinhVienService;

    @Autowired
    public ApiSinhVienController(SinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerSinhVien(@Valid @RequestBody SinhVien sinhVien) {
        // Validate email domain
        if (!sinhVien.getEmail().endsWith("@ou.edu.vn")) {
            return ResponseEntity.badRequest().body("Email phải có miền là @ou.edu.vn");
        }

        // Check if email already exists
        SinhVien existingSinhVien = sinhVienService.findById(sinhVien.getId());
        if (existingSinhVien != null) {
            return ResponseEntity.badRequest().body("Email đã được đăng kí trước đó.");
        }

        // Save the SinhVien
        sinhVien.setNgaySinh(new Date()); // Set default date if not provided
        sinhVienService.save(sinhVien);

        return ResponseEntity.status(HttpStatus.CREATED).body("Bạn đã đăng kí tài khoản thành công");
    }

    // Method to retrieve all possible options for dropdowns
    @GetMapping("/options")
    public ResponseEntity<OptionsResponse> getOptions() {
        List<LopHoc> lopHocs = sinhVienService.getAllLopHocs();
        List<Khoa> khoas = sinhVienService.getAllKhoas();
        List<NganhDaoTao> nganhDaoTaos = sinhVienService.getAllNganhDaoTaos();

        OptionsResponse optionsResponse = new OptionsResponse(lopHocs, khoas, nganhDaoTaos);

        return ResponseEntity.ok(optionsResponse);
    }

    public static class OptionsResponse {
        private List<LopHoc> lopHocs;
        private List<Khoa> khoas;
        private List<NganhDaoTao> nganhDaoTaos;

        public OptionsResponse(List<LopHoc> lopHocs, List<Khoa> khoas, List<NganhDaoTao> nganhDaoTaos) {
            this.lopHocs = lopHocs;
            this.khoas = khoas;
            this.nganhDaoTaos = nganhDaoTaos;
        }

        // Getters and Setters
        public List<LopHoc> getLopHocs() {
            return lopHocs;
        }

        public void setLopHocs(List<LopHoc> lopHocs) {
            this.lopHocs = lopHocs;
        }

        public List<Khoa> getKhoas() {
            return khoas;
        }

        public void setKhoas(List<Khoa> khoas) {
            this.khoas = khoas;
        }

        public List<NganhDaoTao> getNganhDaoTaos() {
            return nganhDaoTaos;
        }

        public void setNganhDaoTaos(List<NganhDaoTao> nganhDaoTaos) {
            this.nganhDaoTaos = nganhDaoTaos;
        }
    }
}
