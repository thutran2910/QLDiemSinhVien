/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.tmt.pojo.SinhVien;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.Khoa;
import com.tmt.pojo.NganhDaoTao;
import com.tmt.repository.SinhVienRepository;
import com.tmt.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SinhVienServiceImpl implements SinhVienService {

    private final SinhVienRepository sinhVienRepository;

    @Autowired
    public SinhVienServiceImpl(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public SinhVien findById(int id) {
        return sinhVienRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> findAll(int page, int pageSize) {
        return sinhVienRepository.findAll(page, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public int countAll() {
        return sinhVienRepository.countAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> findByLopHocId(int lopHocId) {
        return sinhVienRepository.findByLopHocId(lopHocId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> getNameSinhVien() {
        return sinhVienRepository.getNameSinhVien();
    }

    @Override
    @Transactional
    public void save(SinhVien sinhVien) {
        sinhVienRepository.save(sinhVien);
    }

    @Override
    @Transactional
    public void update(SinhVien sinhVien) {
        sinhVienRepository.save(sinhVien);
    }

    @Override
    @Transactional
    public void saveOrUpdate(SinhVien sinhVien) {
        sinhVienRepository.saveOrUpdate(sinhVien);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        sinhVienRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LopHoc> getAllLopHocs() {
        return sinhVienRepository.getAllLopHocs();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Khoa> getAllKhoas() {
        return sinhVienRepository.getAllKhoas();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NganhDaoTao> getAllNganhDaoTaos() {
        return sinhVienRepository.getAllNganhDaoTaos();
    }
}
