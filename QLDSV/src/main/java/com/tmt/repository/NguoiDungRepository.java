/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.repository;

import com.tmt.pojo.NguoiDung;
import java.util.List;

public interface NguoiDungRepository {
    List<NguoiDung> getAllNguoiDung();
    NguoiDung getNguoiDungById(int id);
    void addNguoiDung(NguoiDung nguoiDung);
    void deleteNguoiDung(int id);
}
