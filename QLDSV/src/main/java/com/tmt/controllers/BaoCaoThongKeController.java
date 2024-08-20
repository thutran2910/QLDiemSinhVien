/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.controllers;

import com.tmt.repository.DiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BaoCaoThongKeController {

    @Autowired
    private DiemRepository diemRepository;

    @GetMapping("/bctk")
    public String showStatistics(@RequestParam(value = "monHocId", required = false) Integer monHocId, Model model) {
        // Load all subjects for the dropdown
        model.addAttribute("monHocs", diemRepository.getAllMonHocs());

        if (monHocId != null && monHocId > 0) {
            // Get highest average scores per class for the selected subject
            List<Map.Entry<String, Double>> lopHocScores = diemRepository.getAllLopHocs().stream()
                .map(lopHoc -> {
                    Double maxAverageScore = diemRepository.getAllAverageScores(monHocId, lopHoc.getId())
                        .stream()
                        .map(row -> (Double) row[2])
                        .max(Double::compare)
                        .orElse(0.0);
                    return Map.entry(lopHoc.getName(), maxAverageScore);
                })
                .collect(Collectors.toList());

            // Send data to the view
            model.addAttribute("lopHocScores", lopHocScores);
            model.addAttribute("selectedMonHocId", monHocId); // Keep track of selected subject
        }

        return "BaoCaoThongKe";
    }
}
