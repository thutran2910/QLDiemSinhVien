/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service;

import com.tmt.pojo.TraLoiDienDan;

import java.util.List;

public interface TraLoiDienDanService {

    List<TraLoiDienDan> getTraLoiByDienDan(int dienDanId);

    void addTraLoi(TraLoiDienDan traLoiDienDan);

    void updateTraLoi(TraLoiDienDan traLoiDienDan);

    void deleteTraLoi(int id);
}
