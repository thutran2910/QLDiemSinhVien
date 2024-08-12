/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.MonHoc;
import com.tmt.repository.MonHocRepository;
import com.tmt.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonHocServiceImpl implements MonHocService {

    private final MonHocRepository monHocRepository;

    @Autowired
    public MonHocServiceImpl(MonHocRepository monHocRepository) {
        this.monHocRepository = monHocRepository;
    }

    @Override
    public List<MonHoc> getNameMonHoc() {  // Changed from findAll
        return monHocRepository.getNameMonHoc();
    }
}
