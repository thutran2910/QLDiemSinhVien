/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.TraLoiDienDan;
import com.tmt.repository.TraLoiDienDanRepository;
import com.tmt.service.TraLoiDienDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraLoiDienDanServiceImpl implements TraLoiDienDanService {

    @Autowired
    private TraLoiDienDanRepository traLoiDienDanRepository;

    @Override
    public List<TraLoiDienDan> getTraLoiByDienDan(int dienDanId) {
        return this.traLoiDienDanRepository.getTraLoiByDienDan(dienDanId);
    }

    @Override
    public void addTraLoi(TraLoiDienDan traLoiDienDan) {
        this.traLoiDienDanRepository.addTraLoi(traLoiDienDan);
    }

    @Override
    public void updateTraLoi(TraLoiDienDan traLoiDienDan) {
        this.traLoiDienDanRepository.updateTraLoi(traLoiDienDan);
    }

    @Override
    public void deleteTraLoi(int id) {
        this.traLoiDienDanRepository.deleteTraLoi(id);
    }
}
