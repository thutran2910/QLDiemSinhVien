/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;

import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.SinhVien;
import java.util.List;

public interface LopHocRepository {

    LopHoc findById(int id);

    List<LopHoc> findAll();

    List<SinhVien> getStudentsByLopHocId(int lopHocId, int page, int pageSize);

    List<LopHoc> getNameLopHoc();

    void save(LopHoc lopHoc);

    void update(LopHoc lopHoc);

    void deleteById(int id);

    int countStudentsByLopHocId(int lopHocId);
}
