/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.LoaiDiem;
import com.tmt.repository.LoaiDiemRepository;
import com.tmt.service.LoaiDiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiDiemServiceImpl implements LoaiDiemService {

    private final LoaiDiemRepository loaiDiemRepository;

    @Autowired
    public LoaiDiemServiceImpl(LoaiDiemRepository loaiDiemRepository) {
        this.loaiDiemRepository = loaiDiemRepository;
    }

    @Override
    public List<LoaiDiem> getNameLoaiDiem() {  // Changed from findAll
        return loaiDiemRepository.getNameLoaiDiem();
    }
}
