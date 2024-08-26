/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.DienDan;
import com.tmt.repository.DienDanRepository;
import com.tmt.service.DienDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DienDanServiceImpl implements DienDanService {

    @Autowired
    private DienDanRepository dienDanRepository;

    @Override
    public List<DienDan> getDienDanByMonHoc(int monHocId) {
        return this.dienDanRepository.getDienDanByMonHoc(monHocId);
    }

    @Override
    public DienDan getDienDanById(int id) {
        return this.dienDanRepository.getDienDanById(id);
    }

    @Override
    public void addDienDan(DienDan dienDan) {
        this.dienDanRepository.addDienDan(dienDan);
    }

    @Override
    public void updateDienDan(DienDan dienDan) {
        this.dienDanRepository.updateDienDan(dienDan);
    }

    @Override
    public void deleteDienDan(int id) {
        this.dienDanRepository.deleteDienDan(id);
    }
}
