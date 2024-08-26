/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service;

import com.tmt.pojo.Diem;
import com.tmt.pojo.DiemTrungBinh;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.LoaiDiem;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.SinhVien;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public interface DiemService {

    List<Diem> getAllDiems();

    List<SinhVien> getAllSinhViens();

    List<MonHoc> getAllMonHocs();

    List<HocKy> getAllHocKys();

    List<LopHoc> getAllLopHocs();

    List<LoaiDiem> getAllLoaiDiems();

    List<Diem> getDiemBySinhVienIdAndMonHocIdAndLopHocId(int sinhVienId, int monHocId, int lopHocId);

    List<Diem> getDiemByMonHocIdAndLopHocId(int monHocId, int lopHocId);

    void saveDiem(Diem diem);

    void updateDiem(Diem diem);

    void deleteDiem(int id);

    void exportDiemToPdf(HttpServletResponse response, List<Diem> diemList) throws IOException;

    Double getAverageScoreForStudent(int sinhVienId, int monHocId, int lopHocId);

    List<Object[]> getAllAverageScores(int monHocId, int lopHocId);

    List<Object[]> getHighestAverageScoresByClass(int monHocId);
}
