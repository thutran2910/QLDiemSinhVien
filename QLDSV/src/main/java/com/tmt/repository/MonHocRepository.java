/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;

import com.tmt.pojo.MonHoc;
import java.util.List;

public interface MonHocRepository {
    List<MonHoc> findByGiangVienId(int giangVienId);
    MonHoc findById(int id);
    List<MonHoc> getNameMonHoc();  
}