package com.tmt.controllers;

import com.tmt.repository.DiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class ApiBaoCaoThongKeController {

    @Autowired
    private DiemRepository diemRepository;

    @PreAuthorize("hasRole('GIANGVIEN')")
    @GetMapping("/api/bctk")
    public ResponseEntity<Map<String, Object>> getStatistics(
            @RequestParam(value = "monHocId", required = false) Integer monHocId) {
        
        Map<String, Object> response = new HashMap<>();
        
        response.put("monHocs", diemRepository.getAllMonHocs());

        if (monHocId != null && monHocId > 0) {
            List<Map<String, Object>> lopHocScores = diemRepository.getAllLopHocs().stream()
                .map(lopHoc -> {
                    Double maxAverageScore = diemRepository.getAllAverageScores(monHocId, lopHoc.getId())
                        .stream()
                        .map(row -> (Double) row[2])
                        .max(Double::compare)
                        .orElse(0.0);
                    Map<String, Object> scoreMap = new HashMap<>();
                    scoreMap.put("lopHocName", lopHoc.getName());
                    scoreMap.put("maxAverageScore", maxAverageScore);
                    return scoreMap;
                })
                .collect(Collectors.toList());

            response.put("lopHocScores", lopHocScores);
            response.put("selectedMonHocId", monHocId); 
        }

        return ResponseEntity.ok(response);
    }
}
