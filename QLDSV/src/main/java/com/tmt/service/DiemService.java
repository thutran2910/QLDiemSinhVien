/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service;

import com.tmt.pojo.Diem;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.LoaiDiem;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.SinhVien;

import java.util.List;

public interface DiemService {

    List<Diem> getAllDiems();

    List<SinhVien> getAllSinhViens();

    List<MonHoc> getAllMonHocs();

    List<HocKy> getAllHocKys();
    
    List<LopHoc> getAllLopHocs();

    List<LoaiDiem> getAllLoaiDiems();
    
    List<Diem> getDiemBySinhVienId(int sinhVienId);

    List<Diem> getDiemBySinhVienIdAndMonHocIdAndLopHocId(int sinhVienId, int monHocId, int lopHocId);

    List<Diem> getDiemByMonHocIdAndLopHocId(int monHocId, int lopHocId);

    void saveDiem(Diem diem);

    void updateDiem(Diem diem);

    void deleteDiem(int id);
//    float calculateAverageScore(int sinhVienId, int monHocId);
}
