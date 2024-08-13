package com.tmt.controllers;

import com.tmt.pojo.LopHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.service.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lophoc")
public class ApiLopHocController {

    @Autowired
    private LopHocService lopHocService;

    // Get all LopHoc
    @GetMapping
    public ResponseEntity<List<LopHoc>> getAllLopHoc() {
        List<LopHoc> lopHocs = lopHocService.findAll();
        return new ResponseEntity<>(lopHocs, HttpStatus.OK);
    }

    // Get LopHoc by ID
    @GetMapping("/{id}")
    public ResponseEntity<LopHoc> getLopHocById(@PathVariable("id") int id) {
        LopHoc lopHoc = lopHocService.findById(id);
        if (lopHoc != null) {
            return new ResponseEntity<>(lopHoc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create new LopHoc
    @PostMapping
    public ResponseEntity<LopHoc> createLopHoc(@RequestBody LopHoc lopHoc) {
        lopHocService.save(lopHoc);
        return new ResponseEntity<>(lopHoc, HttpStatus.CREATED);
    }

    // Update LopHoc
    @PutMapping("/{id}")
    public ResponseEntity<LopHoc> updateLopHoc(@PathVariable("id") int id, @RequestBody LopHoc lopHoc) {
        LopHoc existingLopHoc = lopHocService.findById(id);
        if (existingLopHoc != null) {
            lopHoc.setId(id);  // Ensure that the ID is correctly set
            lopHocService.update(lopHoc);
            return new ResponseEntity<>(lopHoc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete LopHoc
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLopHoc(@PathVariable("id") int id) {
        LopHoc lopHoc = lopHocService.findById(id);
        if (lopHoc != null) {
            lopHocService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get students by LopHoc ID
    @GetMapping("/{id}/students")
    public ResponseEntity<List<SinhVien>> getStudentsByLopHocId(@PathVariable("id") int id) {
        List<SinhVien> students = lopHocService.getStudentsByLopHocId(id);
        if (students != null) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all LopHoc names
    @GetMapping("/names")
    public ResponseEntity<List<LopHoc>> getNameLopHoc() {
        List<LopHoc> lopHocs = lopHocService.getNameLopHoc();
        return new ResponseEntity<>(lopHocs, HttpStatus.OK);
    }
}
