/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service;

import com.tmt.pojo.GiangVien;
import java.util.List;

public interface GiangVienService {
    GiangVien findById(int id);
    List<GiangVien> findAll();
    void save(GiangVien giangVien);
    void update(GiangVien giangVien);
    void deleteById(int id);
}
