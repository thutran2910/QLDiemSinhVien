/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.HocKy;
import com.tmt.repository.HocKyRepository;
import com.tmt.service.HocKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HocKyServiceImpl implements HocKyService {

    private final HocKyRepository hocKyRepository;

    @Autowired
    public HocKyServiceImpl(HocKyRepository hocKyRepository) {
        this.hocKyRepository = hocKyRepository;
    }

    @Override
    public List<HocKy> getNameHocKy() {
        return hocKyRepository.getNameHocKy();
    }
}
