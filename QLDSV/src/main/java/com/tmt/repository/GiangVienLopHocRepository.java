/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;

import com.tmt.pojo.GiangVienLopHoc;
import java.util.List;

public interface GiangVienLopHocRepository {
    List<GiangVienLopHoc> findByLopHocId(int lopHocId);
    List<GiangVienLopHoc> findByGiangVienId(int giangVienId);
    void save(GiangVienLopHoc giangVienLopHoc);
    void deleteById(int giangVienId, int lopHocId);
}
