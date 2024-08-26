/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service;

import com.tmt.pojo.DienDan;

import java.util.List;

public interface DienDanService {

    List<DienDan> getDienDanByMonHoc(int monHocId);

    DienDan getDienDanById(int id);

    void addDienDan(DienDan dienDan);

    void updateDienDan(DienDan dienDan);

    void deleteDienDan(int id);
}
