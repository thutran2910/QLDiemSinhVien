/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.VaiTro;
import com.tmt.repository.VaiTroRepository;
import com.tmt.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VaiTroServiceImpl implements VaiTroService {
    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    @Transactional
    public List<VaiTro> getVaiTro() {
        return vaiTroRepository.getVaiTro();
    }
}