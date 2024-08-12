/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service;

import com.tmt.pojo.NguoiDung;
import com.tmt.pojo.SinhVien;
import java.util.List;

public interface NguoiDungService {
    List<NguoiDung> getNguoiDung();
    List<NguoiDung> getAllNguoiDung();
    NguoiDung getNguoiDungById(int id);
    void addNguoiDung(NguoiDung nguoiDung);
    void deleteNguoiDung(int id);
}
