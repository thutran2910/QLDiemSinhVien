/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.controllers;

import com.tmt.pojo.LopHoc;
import com.tmt.pojo.GiangVienLopHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.service.LopHocService;
import com.tmt.service.GiangVienService;
import com.tmt.service.GiangVienLopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class LopHocController {

    @Autowired
    private LopHocService lopHocService;

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private GiangVienLopHocService giangVienLopHocService;

    @GetMapping("/dslop")
    public String listLopHoc(Model model) {
        List<LopHoc> lopHocList = lopHocService.findAll();
        model.addAttribute("lopHocList", lopHocList);
        return "DanhSachLopHoc";
    }

    @GetMapping("/lophoc/{id}")
    public String getLopHocDetails(@PathVariable int id, Model model) {
        LopHoc lopHoc = lopHocService.findById(id);
        if (lopHoc != null) {
            List<SinhVien> sinhVienList = lopHocService.getStudentsByLopHocId(id);
            List<GiangVienLopHoc> giangVienLopHocList = giangVienLopHocService.findByLopHocId(id);
            model.addAttribute("lopHoc", lopHoc);
            model.addAttribute("giangVienLopHocList", giangVienLopHocList);
            model.addAttribute("sinhVienList", sinhVienList);
        }
        return "ChiTietLopHoc";
    }

}
