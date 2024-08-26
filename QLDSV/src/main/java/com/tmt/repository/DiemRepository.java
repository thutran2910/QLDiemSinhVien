/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tmt.repository;

import com.tmt.pojo.Diem;
import com.tmt.pojo.DiemTrungBinh;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.Khoa;
import com.tmt.pojo.LoaiDiem;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.NganhDaoTao;
import com.tmt.pojo.SinhVien;

import java.util.List;
import java.util.Map;

public interface DiemRepository {

    List<Diem> getAllDiems();

    List<Diem> getDiemBySinhVienIdAndMonHocIdAndLopHocId(int sinhVienId, int monHocId, int lopHocId);

    List<SinhVien> getAllSinhViens();

    List<MonHoc> getAllMonHocs();

    List<LopHoc> getAllLopHocs();

    List<HocKy> getAllHocKys();

    List<LoaiDiem> getAllLoaiDiems();

    List<Diem> getDiemByMonHocIdAndLopHocId(int monHocId, int lopHocId);

    void saveDiem(Diem diem);

    void updateDiem(Diem diem);

    void deleteDiem(int id);

    List<Diem> getDiemByLoaiDiemId(int loaiDiemId);

    List<Diem> getDiemBySinhVienId(int sinhVienId);

    Double getAverageScoreForStudent(int sinhVienId, int monHocId, int lopHocId);
    
    List<Object[]> getAllAverageScores(int monHocId, int lopHocId);
    
    List<Object[]> getHighestAverageScoresByClass(int monHocId);

}