/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.NguoiDung;
import com.tmt.repository.NguoiDungRepository;
import com.tmt.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    @Transactional
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungRepository.getAllNguoiDung();
    }

    @Override
    @Transactional
    public NguoiDung getNguoiDungById(int id) {
        return nguoiDungRepository.getNguoiDungById(id);
    }

    @Override
    @Transactional
    public void addNguoiDung(NguoiDung nguoiDung) {
        nguoiDungRepository.addNguoiDung(nguoiDung);
    }

    @Override
    @Transactional
    public void deleteNguoiDung(int id) {
        nguoiDungRepository.deleteNguoiDung(id);
    }

    @Override
    public List<NguoiDung> getNguoiDung() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
