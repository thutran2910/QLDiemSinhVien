/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.GiangVienLopHoc;
import com.tmt.repository.GiangVienLopHocRepository;
import com.tmt.service.GiangVienLopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GiangVienLopHocServiceImpl implements GiangVienLopHocService {

    @Autowired
    private GiangVienLopHocRepository giangVienLopHocRepository;

    @Override
    public List<GiangVienLopHoc> findByLopHocId(int lopHocId) {
        return giangVienLopHocRepository.findByLopHocId(lopHocId);
    }

    @Override
    public List<GiangVienLopHoc> findByGiangVienId(int giangVienId) {
        return giangVienLopHocRepository.findByGiangVienId(giangVienId);
    }

    @Override
    public void save(GiangVienLopHoc giangVienLopHoc) {
        giangVienLopHocRepository.save(giangVienLopHoc);
    }

    @Override
    public void deleteById(int giangVienId, int lopHocId) {
        giangVienLopHocRepository.deleteById(giangVienId, lopHocId);
    }
}
