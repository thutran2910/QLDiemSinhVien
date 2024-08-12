/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.GiangVien;
import com.tmt.repository.GiangVienRepository;
import com.tmt.service.GiangVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Override
    public GiangVien findById(int id) {
        return giangVienRepository.findById(id);
    }

    @Override
    public List<GiangVien> findAll() {
        return giangVienRepository.findAll();
    }

    @Override
    public void save(GiangVien giangVien) {
        giangVienRepository.save(giangVien);
    }

    @Override
    public void update(GiangVien giangVien) {
        giangVienRepository.update(giangVien);
    }

    @Override
    public void deleteById(int id) {
        giangVienRepository.deleteById(id);
    }
}
