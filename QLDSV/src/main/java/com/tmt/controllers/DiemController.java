/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.controllers;

import com.tmt.pojo.Diem;
import com.tmt.pojo.DiemTrungBinh;
import com.tmt.pojo.HocKy;
import com.tmt.pojo.LoaiDiem;
import com.tmt.pojo.LopHoc;
import com.tmt.pojo.MonHoc;
import com.tmt.pojo.SinhVien;
import com.tmt.service.DiemService;
import com.tmt.service.LopHocService;
import com.tmt.service.MonHocService;
import com.tmt.service.SinhVienService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DiemController {
    private final DiemService diemService;
    private final MonHocService monHocService;
    private final LopHocService lopHocService;
    private final SinhVienService sinhVienService;

    @Autowired
    public DiemController(DiemService diemService, MonHocService monHocService, SinhVienService sinhVienService, LopHocService lopHocService) {
        this.diemService = diemService;
        this.monHocService = monHocService;
        this.sinhVienService = sinhVienService;
        this.lopHocService = lopHocService;
    }

    @GetMapping("/diem")
    public String showScoresBySubject(@RequestParam(value = "monHocId", required = false, defaultValue = "0") int monHocId,
                                      @RequestParam(value = "lopHocId", required = false, defaultValue = "0") int lopHocId,
                                      Model model) {
        if (monHocId > 0 && lopHocId > 0) {
            List<Diem> diemList = diemService.getDiemByMonHocIdAndLopHocId(monHocId, lopHocId);
            List<MonHoc> monHocList = monHocService.getNameMonHoc();
            List<LopHoc> lopHocList = lopHocService.getNameLopHoc(); // Fixed service call
            model.addAttribute("diemList", diemList);
            model.addAttribute("monHocList", monHocList);
            model.addAttribute("lopHocList", lopHocList);
            model.addAttribute("selectedMonHocId", monHocId);
            model.addAttribute("selectedLopHocId", lopHocId);
        } else {
            model.addAttribute("error", "Both MonHocId and LopHocId are required");
            model.addAttribute("monHocList", monHocService.getNameMonHoc());
            model.addAttribute("lopHocList", lopHocService.getNameLopHoc()); // Ensure lopHocList is available
        }
        return "DanhSachDiem";
    }

    @GetMapping("/diem/form")
    public String showDiemForm(Model model) {
        model.addAttribute("diem", new Diem());
        model.addAttribute("sinhViens", diemService.getAllSinhViens());
        model.addAttribute("monHocs", diemService.getAllMonHocs());
        model.addAttribute("lopHocs", diemService.getAllLopHocs());
        model.addAttribute("hocKys", diemService.getAllHocKys());
        model.addAttribute("loaiDiems", diemService.getAllLoaiDiems());
        return "NhapDiem"; 
    }

    @PostMapping("/diem/save")
    public String saveDiem(@ModelAttribute Diem diem) {
        if (diem.getId() == 0) {
            diemService.saveDiem(diem);
        } else {
            diemService.updateDiem(diem);
        }
        return "redirect:/diem?monHocId=" + diem.getMonHoc().getId();  // Redirect to display updated scores
    }
    
    @GetMapping("/diem/export/csv")
public void exportToCSV(@RequestParam(value = "monHocId", required = false, defaultValue = "0") int monHocId,
                         @RequestParam(value = "lopHocId", required = false, defaultValue = "0") int lopHocId,
                         HttpServletResponse response) throws IOException {
    List<Diem> diemList = diemService.getDiemByMonHocIdAndLopHocId(monHocId, lopHocId);

    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=diem.csv");

    try (PrintWriter writer = response.getWriter()) {
        writer.println("Sinh vien,Lop hoc,Mon hoc,Diem,Loai diem");
        for (Diem diem : diemList) {
            writer.printf("%s,%s,%s,%s,%s%n",
                diem.getSinhVien().getName(),
                diem.getLopHoc().getName(),
                diem.getMonHoc().getName(),
                diem.getScore(),
                diem.getLoaiDiem().getName());
        }
    }
}


//    private List<DiemTrungBinh> calculateAverageScores(List<SinhVien> sinhViens, int monHocId) {
//        List<DiemTrungBinh> diemTrungBinh = new ArrayList<>();
//        for (SinhVien sinhVien : sinhViens) {
//            float averageScore = diemService.calculateAverageScore(sinhVien.getId(), monHocId);
//            diemTrungBinh.add(new DiemTrungBinh(sinhVien.getName(), averageScore));
//        }
//        return diemTrungBinh;
//    }
}

