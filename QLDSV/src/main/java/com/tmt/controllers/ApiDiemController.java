/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.controllers;

import com.tmt.pojo.Diem;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.LoaiDiem;
import com.tmt.service.DiemService;
import com.tmt.service.MonHocService;
import com.tmt.service.LopHocService;
import com.tmt.service.SinhVienService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diem")
public class ApiDiemController {

    private final DiemService diemService;
    private final MonHocService monHocService;
    private final LopHocService lopHocService;
    private final SinhVienService sinhVienService;

    @Autowired
    public ApiDiemController(DiemService diemService, MonHocService monHocService, SinhVienService sinhVienService, LopHocService lopHocService) {
        this.diemService = diemService;
        this.monHocService = monHocService;
        this.sinhVienService = sinhVienService;
        this.lopHocService = lopHocService;
    }

    @GetMapping
    public ResponseEntity<List<Diem>> getAllDiems() {
        List<Diem> diems = diemService.getAllDiems();
        return ResponseEntity.ok(diems);
    }

    @GetMapping("/by-sinhvien-monhoc-lophoc")
    public ResponseEntity<List<Diem>> getDiemBySinhVienIdAndMonHocIdAndLopHocId(
            @RequestParam int sinhVienId,
            @RequestParam int monHocId,
            @RequestParam int lopHocId) {
        List<Diem> diems = diemService.getDiemBySinhVienIdAndMonHocIdAndLopHocId(sinhVienId, monHocId, lopHocId);
        return ResponseEntity.ok(diems);
    }

    @GetMapping("/by-monhoc-lophoc")
    public ResponseEntity<List<Diem>> getDiemByMonHocIdAndLopHocId(
            @RequestParam int monHocId,
            @RequestParam int lopHocId) {
        List<Diem> diems = diemService.getDiemByMonHocIdAndLopHocId(monHocId, lopHocId);
        return ResponseEntity.ok(diems);
    }

    @GetMapping("/sinhviens")
    public ResponseEntity<List<SinhVien>> getAllSinhViens() {
        List<SinhVien> sinhViens = diemService.getAllSinhViens();
        return ResponseEntity.ok(sinhViens);
    }

    @GetMapping("/monhocs")
    public ResponseEntity<List<MonHoc>> getAllMonHocs() {
        List<MonHoc> monHocs = diemService.getAllMonHocs();
        return ResponseEntity.ok(monHocs);
    }

    @GetMapping("/lophocs")
    public ResponseEntity<List<LopHoc>> getAllLopHocs() {
        List<LopHoc> lopHocs = diemService.getAllLopHocs();
        return ResponseEntity.ok(lopHocs);
    }

    @GetMapping("/hocKys")
    public ResponseEntity<List<HocKy>> getAllHocKys() {
        List<HocKy> hocKys = diemService.getAllHocKys();
        return ResponseEntity.ok(hocKys);
    }

    @GetMapping("/loaidiems")
    public ResponseEntity<List<LoaiDiem>> getAllLoaiDiems() {
        List<LoaiDiem> loaiDiems = diemService.getAllLoaiDiems();
        return ResponseEntity.ok(loaiDiems);
    }

    

    @PostMapping
    public ResponseEntity<Diem> createDiem(@RequestBody Diem diem) {
        diemService.saveDiem(diem);
        return ResponseEntity.ok(diem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diem> updateDiem(@PathVariable int id, @RequestBody Diem diem) {
        diem.setId(id);
        diemService.updateDiem(diem);
        return ResponseEntity.ok(diem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiem(@PathVariable int id) {
        diemService.deleteDiem(id);
        return ResponseEntity.noContent().build();
    }
}
