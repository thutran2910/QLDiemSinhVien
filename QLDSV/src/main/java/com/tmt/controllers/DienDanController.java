/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.controllers;

import com.tmt.pojo.DienDan;
import com.tmt.pojo.SinhVien;
import com.tmt.pojo.TraLoiDienDan;
import com.tmt.service.DienDanService;
import com.tmt.service.SinhVienService;
import com.tmt.service.TraLoiDienDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping("/diendan")
public class DienDanController {

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private DienDanService dienDanService;

    @Autowired
    private TraLoiDienDanService traLoiDienDanService;

    @GetMapping("/{monHocId}")
    public String listDienDan(@PathVariable("monHocId") int monHocId, Model model) {
        List<DienDan> dienDans = dienDanService.getDienDanByMonHoc(monHocId);
        model.addAttribute("dienDans", dienDans);
        model.addAttribute("monHocId", monHocId);
        return "DienDanList";
    }

    @GetMapping("/{dienDanId}/tra-loi")
    public String listTraLoi(@PathVariable("dienDanId") int dienDanId, Model model) {
        List<TraLoiDienDan> traLoiDienDans = traLoiDienDanService.getTraLoiByDienDan(dienDanId);
        model.addAttribute("traLoiDienDans", traLoiDienDans);
        model.addAttribute("dienDanId", dienDanId);
        return "TraLoiDienDanList";
    }

    @PostMapping("/{dienDanId}/tra-loi")
    public String addTraLoi(@PathVariable("dienDanId") int dienDanId, @RequestParam("content") String content) {
        // Retrieve the currently logged-in user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        
        // Fetch the list of SinhVien
        List<SinhVien> sinhViens = sinhVienService.getNameSinhVien();
        
        // Find the SinhVien object corresponding to the logged-in user
        SinhVien sinhVien = sinhViens.stream()
                .filter(sv -> sv.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("SinhVien not found for username: " + username));
        
        // Fetch DienDan entity based on ID
        DienDan dienDan = dienDanService.getDienDanById(dienDanId);
        
        // Create and save TraLoiDienDan
        TraLoiDienDan traLoiDienDan = new TraLoiDienDan();
        traLoiDienDan.setContent(content);
        traLoiDienDan.setDienDan(dienDan);
        traLoiDienDan.setSinhVien(sinhVien);
        traLoiDienDanService.addTraLoi(traLoiDienDan);
        
        return "redirect:/diendan/" + dienDanId + "/tra-loi";
    }
}
