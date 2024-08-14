/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.Diem;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.LoaiDiem;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.repository.DiemRepository;
import com.tmt.service.DiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiemServiceImpl implements DiemService {

    private final DiemRepository diemRepository;

    @Autowired
    public DiemServiceImpl(DiemRepository diemRepository) {
        this.diemRepository = diemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diem> getAllDiems() {
        return diemRepository.getAllDiems();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> getAllSinhViens() {
        return diemRepository.getAllSinhViens();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MonHoc> getAllMonHocs() {
        return diemRepository.getAllMonHocs();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LopHoc> getAllLopHocs() {
        return diemRepository.getAllLopHocs();
    }


    @Override
    @Transactional(readOnly = true)
    public List<HocKy> getAllHocKys() {
        return diemRepository.getAllHocKys();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoaiDiem> getAllLoaiDiems() {
        return diemRepository.getAllLoaiDiems();
    }

    @Override
    @Transactional
    public void saveDiem(Diem diem) {
        diemRepository.saveDiem(diem);
    }

    @Override
    @Transactional
    public void updateDiem(Diem diem) {
        diemRepository.updateDiem(diem);
    }

    @Override
    @Transactional
    public void deleteDiem(int id) {
        diemRepository.deleteDiem(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diem> getDiemBySinhVienIdAndMonHocIdAndLopHocId(int sinhVienId, int monHocId, int lopHocId) {
        return diemRepository.getDiemBySinhVienIdAndMonHocIdAndLopHocId(sinhVienId, monHocId, lopHocId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diem> getDiemByMonHocIdAndLopHocId(int monHocId, int lopHocId) {
        return diemRepository.getDiemByMonHocIdAndLopHocId(monHocId, lopHocId);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public float calculateAverageScore(int sinhVienId, int monHocId) {
//        // Lấy tất cả điểm của sinh viên và môn học
//        List<Diem> diemList = diemRepository.getDiemBySinhVienIdAndMonHocIdAndLopHocId(sinhVienId, monHocId, lopHocId);
//        float totalMidtermScore = 0;
//        float totalFinalScore = 0;
//        int countMidterm = 0;
//        int countFinal = 0;
//
//        // Lặp qua các điểm và tính tổng điểm giữa kỳ và cuối kỳ
//        for (Diem diem : diemList) {
//            if (diem.getLoaiDiem().getName().equalsIgnoreCase("Diem giua ki")) {
//                totalMidtermScore += diem.getScore();
//                countMidterm++;
//            } else if (diem.getLoaiDiem().getName().equalsIgnoreCase("Diem cuoi ki")) {
//                totalFinalScore += diem.getScore();
//                countFinal++;
//            }
//        }
//
//        // Tính điểm trung bình
//        float averageMidterm = countMidterm > 0 ? totalMidtermScore / countMidterm : 0;
//        float averageFinal = countFinal > 0 ? totalFinalScore / countFinal : 0;
//
//        // Điểm trung bình = 50% điểm giữa kỳ + 50% điểm cuối kỳ
//        return (averageMidterm * 0.5f) + (averageFinal * 0.5f);
//    }

    @Override
    public List<Diem> getDiemBySinhVienId(int sinhVienId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
